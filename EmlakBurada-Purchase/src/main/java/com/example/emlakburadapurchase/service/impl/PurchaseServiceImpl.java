package com.example.emlakburadapurchase.service.impl;


import com.example.emlakburadapurchase.client.PaymentClient;
import com.example.emlakburadapurchase.client.UserClient;
import com.example.emlakburadapurchase.client.request.PaymentRequest;
import com.example.emlakburadapurchase.client.request.UserBalanceRequest;
import com.example.emlakburadapurchase.client.response.IndividualUserResponse;
import com.example.emlakburadapurchase.dto.request.PurchaseRequest;
import com.example.emlakburadapurchase.dto.request.PurchaseStatusRequest;
import com.example.emlakburadapurchase.dto.request.UserBalanceIdentificationRequest;
import com.example.emlakburadapurchase.dto.response.PurchaseResponse;
import com.example.emlakburadapurchase.exception.PaymentFailedException;
import com.example.emlakburadapurchase.exception.PurchaseNotFoundException;
import com.example.emlakburadapurchase.model.Purchase;
import com.example.emlakburadapurchase.model.enums.PurchaseStatus;
import com.example.emlakburadapurchase.queue.QueueService;
import com.example.emlakburadapurchase.repository.PurchaseDao;
import com.example.emlakburadapurchase.service.PurchaseService;
import com.example.emlakburadapurchase.utils.dtoConverter.DtoConverterService;
import com.example.emlakburadapurchase.utils.results.DataResult;
import com.example.emlakburadapurchase.utils.results.Result;
import com.example.emlakburadapurchase.utils.results.SuccessDataResult;
import com.example.emlakburadapurchase.utils.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.emlakburadapurchase.packages.AdvertPackage;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseDao purchaseDao;
    private DtoConverterService dtoConverterService;
    private PaymentClient paymentClient;
    private QueueService rabbitMqService;
    private UserClient userClient;

    @Autowired
    public PurchaseServiceImpl(PurchaseDao purchaseDao, DtoConverterService dtoConverterService, PaymentClient paymentClient,
                               QueueService rabbitMqService, UserClient userClient) {
        this.purchaseDao = purchaseDao;
        this.dtoConverterService = dtoConverterService;
        this.paymentClient = paymentClient;
        this.rabbitMqService = rabbitMqService;
        this.userClient = userClient;
    }


    // t??m sat??n almalar?? getirir
    @Override
    public DataResult<List<PurchaseResponse>> findAll() {
        return new SuccessDataResult<List<PurchaseResponse>>(dtoConverterService.entityToDto(purchaseDao.findAll(), PurchaseResponse.class),
                "all purchases listed");
    }


    // login yapan kullan??c??ya g??re sat??n alma yapar
    //kullan??c??n??n kalan haklar??n?? kontrol eder. Paketin s??resi ge??mi??se kalan haklar??n?? s??f??rlar. ??demeyi bekler. ??deme yap??ld??ysa, RabbitMq ??zerinden user'a tan??mlan??r.
    // S??resi ge??memi??se s??f??rlama yapmaz ??demeyi bekler. ??deme yap??ld??ysa, RabbitMq ??zerinden user'a tan??mlan??r.
    @Override
    public Result purchase(PurchaseRequest purchaseRequest,int userId) {
        purchaseRequest.setUserId(userId);
        DataResult<IndividualUserResponse> response = userClient.findById(purchaseRequest.getUserId()); // Feign Exception  GlobalExceptionHandler taraf??ndan handle ediliyor. User bulunmazsa exception at??yor.

        UserBalanceRequest userBalanceRequest = new UserBalanceRequest();
        if (response.getData().getEndDateOfPackage().isBefore(LocalDate.now())) {
            userBalanceRequest.setAdvertBalance(0); // userdaki balance s??f??rlan??r
            userBalanceRequest.setEndDateOfPackage(LocalDate.now()); // paketin s??resi ge??mi??se; paket biti?? tarihi, paket sat??n al??m?? i??in paketin al??nd?????? tarihe ??ekilir.
            userClient.updateBalanceById(purchaseRequest.getUserId(), userBalanceRequest);//userclient hata verirse alt sat??ra ge??emez feign exception atar
            response.getData().setAdvertBalance(0); // response nesnesindeki balance s??f??rlan??r
        }
        AdvertPackage advertPackage = new AdvertPackage();

        if (paymentClient.pay(preparePaymentRequest(advertPackage)).isSuccess() == false) { // ??deme ba??ar??s??z olursa exception atar
            throw new PaymentFailedException("payment failed");
        }

        rabbitMqService.sendMessage(prepareUserBalanceIdentificationRequest(advertPackage, response, purchaseRequest));
        purchaseRequest.setPackageId(advertPackage.getId());
        purchaseRequest.setPurchaseStatus(PurchaseStatus.PURCHASED);
        purchaseDao.save((Purchase) dtoConverterService.dtoToEntity(purchaseRequest, Purchase.class));
        return new SuccessResult(" purchase successfull");
    }


    // iade durumu i??in update methodu
    @Override
    public Result updatePurchaseStatusById(int purchaseId, PurchaseStatusRequest purchaseStatusRequest) {

        if (purchaseDao.findById(purchaseId) == null) {
            throw new PurchaseNotFoundException("purchase not found");
        }

        Purchase purchase =  purchaseDao.findById(purchaseId);
        purchase.setPurchaseStatus(purchaseStatusRequest.getPurchaseStatus());

        purchaseDao.save(purchase);

        return new SuccessResult("purchase updated");
    }


    // id ye g??re sat??n al??nan paketi getirir
    @Override
    public DataResult<PurchaseResponse> findById(int purchaseId) {
        if (purchaseDao.findById(purchaseId) == null) {
            throw new PurchaseNotFoundException("purchase not found");
        }

        return new SuccessDataResult<PurchaseResponse>((PurchaseResponse) dtoConverterService.entityToDtoSingle(purchaseDao.findById(purchaseId), PurchaseResponse.class),
                " purchase found.");
    }

    //kullan??c?? id sine g??re sat??n al??nan paketleri getirir
    @Override
    public DataResult<List<PurchaseResponse>> findAllByUserId(int userId) {
        // Feign Exception  GlobalExceptionHandler taraf??ndan handle ediliyor.
        userClient.findById(userId);
        return new SuccessDataResult<List<PurchaseResponse>>(dtoConverterService.entityToDto(purchaseDao.findAllByUserId(userId), PurchaseResponse.class),
                "active purchases listed by user id");
    }

    private PaymentRequest preparePaymentRequest( AdvertPackage advertPackage) {
        PaymentRequest paymentRequest =  new PaymentRequest();
        paymentRequest.setAmount(advertPackage.getPrice());
        return paymentRequest;
    }

    private UserBalanceIdentificationRequest prepareUserBalanceIdentificationRequest(AdvertPackage advertPackage, DataResult<IndividualUserResponse> response, PurchaseRequest purchaseRequest) {
        UserBalanceIdentificationRequest userBalanceIdentificationRequest = new UserBalanceIdentificationRequest();
        userBalanceIdentificationRequest.setAdvertBalance(response.getData().getAdvertBalance() + advertPackage.getNumberOfAdvertRights()); // kullan??c??n??n ilan hakk?? ile pakketteki hak toplan??r
        userBalanceIdentificationRequest.setAdditionalDayEndDateOfPackage(advertPackage.getPackageValidityPeriod());// kullan??c??n??n paket s??resine eklenecek g??n say??s??
        userBalanceIdentificationRequest.setUserId(purchaseRequest.getUserId()); // kullan??c?? id si tan??mland??
        return userBalanceIdentificationRequest;
    }
}
