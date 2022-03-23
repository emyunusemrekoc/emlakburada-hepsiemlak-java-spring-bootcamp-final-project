package com.example.emlakburadauser.service.impl;

import com.example.emlakburadauser.dto.request.IndividualUserRequest;
import com.example.emlakburadauser.dto.request.UserBalanceIdentificationRequest;
import com.example.emlakburadauser.dto.request.UserBalanceRequest;
import com.example.emlakburadauser.dto.response.IndividualUserResponse;
import com.example.emlakburadauser.dto.response.UserBalanceResponse;
import com.example.emlakburadauser.exception.UserNotFoundException;
import com.example.emlakburadauser.model.IndividualUser;
import com.example.emlakburadauser.repository.IndividiualUserDao;
import com.example.emlakburadauser.service.IndividiualUserService;
import com.example.emlakburadauser.utils.dtoConverter.DtoConverterService;
import com.example.emlakburadauser.utils.results.DataResult;
import com.example.emlakburadauser.utils.results.Result;
import com.example.emlakburadauser.utils.results.SuccessDataResult;
import com.example.emlakburadauser.utils.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualUserServiceImpl implements IndividiualUserService {

    private IndividiualUserDao individiualUserDao;
    private DtoConverterService dtoConverterService;

    @Autowired
    public IndividualUserServiceImpl(IndividiualUserDao individiualUserDao, DtoConverterService dtoConverterService) {
        this.individiualUserDao = individiualUserDao;
        this.dtoConverterService = dtoConverterService;
    }


    @Override
    public DataResult<List<IndividualUserResponse>> findAll() {

        return new SuccessDataResult<List<IndividualUserResponse>>(dtoConverterService.entityToDto(individiualUserDao.findAll(), IndividualUserResponse.class),
                " all users listed");

    }

    //user ekleme
    @Override
    public Result add(IndividualUserRequest individualUserRequest) {

        individiualUserDao.save((IndividualUser) dtoConverterService.dtoToEntity(individualUserRequest, IndividualUser.class));

        return new SuccessResult(" user added");
    }

    //id ye göre user güncelleme
    @Override
    public Result updateById(int individualUserId, IndividualUserRequest individualUserRequest) {

        if (individiualUserDao.findById(individualUserId) == null) {
            throw new UserNotFoundException("user not found");
        }
        IndividualUser individualUser = individiualUserDao.findById(individualUserId);
        //IndividualUser individualUser = (IndividualUser) dtoConverterService.dtoToEntity(individualUserRequest, IndividualUser.class);

        prepareIndividualUser(individualUser,individualUserRequest);

        individiualUserDao.save(individualUser);

        return new SuccessResult(" user updated");
    }


    //id ye göre user silme
    @Override
    public Result deleteById(int individualUserId) {
        if (individiualUserDao.findById(individualUserId) == null) {
            throw new UserNotFoundException("user not found");
        }

        individiualUserDao.deleteById(individualUserId);
        return new SuccessResult("user deleted");
    }

    //id ye göre user bulma
    @Override
    public DataResult<IndividualUserResponse> findById(int individualUserId) {

        if (individiualUserDao.findById(individualUserId) == null) {
            throw new UserNotFoundException("user not found");
        }
        return new SuccessDataResult<IndividualUserResponse>((IndividualUserResponse) dtoConverterService.entityToDtoSingle(individiualUserDao.findById(individualUserId), IndividualUserResponse.class),
                " user found.");
    }

    //id ye göre user bakiyesi güncelleme
    @Override
    public Result updateBalanceById(int individualUserId, UserBalanceRequest userBalanceRequest) {

        if (individiualUserDao.findById(individualUserId) == null) {
            throw new UserNotFoundException("user not found");
        }
        IndividualUser individualUser = individiualUserDao.findById(individualUserId);
        individualUser.setAdvertBalance(userBalanceRequest.getAdvertBalance());
        individualUser.setEndDateOfPackage(userBalanceRequest.getEndDateOfPackage());

        individiualUserDao.save(individualUser);

        return new SuccessResult(" balance updated");
    }

    // id ye göre user bakiyesi güncelleme (rabbitMQ)
    @Override
    public Result updateBalanceByRabbitMq(int individualUserId, UserBalanceIdentificationRequest userBalanceIdentificationRequest) {


        IndividualUser individualUser = individiualUserDao.findById(individualUserId);

        individualUser.setId(individualUserId);
        individualUser.setAdvertBalance(userBalanceIdentificationRequest.getAdvertBalance());
        individualUser.setEndDateOfPackage(individualUser.getEndDateOfPackage().plusDays(userBalanceIdentificationRequest.getAdditionalDayEndDateOfPackage()));
        individiualUserDao.save(individualUser);
        return new SuccessResult(" balance updated");
    }

    //id ye göre bakiye sorgulama
    @Override
    public DataResult<UserBalanceResponse> findByIdForBalance(int individualUserId) {
        if (individiualUserDao.findById(individualUserId) == null) {
            throw new UserNotFoundException("user not found");
        }
        return new SuccessDataResult<UserBalanceResponse>((UserBalanceResponse) dtoConverterService.entityToDtoSingle(individiualUserDao.findById(individualUserId), UserBalanceResponse.class),
                " balance listed by user id.");
    }

    private IndividualUser  prepareIndividualUser(IndividualUser individualUser,IndividualUserRequest individualUserRequest) {
        individualUser.setFirstName(individualUserRequest.getFirstName());
        individualUser.setLastName(individualUserRequest.getLastName());
        individualUser.setEmail(individualUserRequest.getEmail());
        individualUser.setPassword(individualUserRequest.getPassword());
        individualUser.setPhoto(individualUserRequest.getPhoto());
        individualUser.setPhoneNumber(individualUserRequest.getPhoneNumber());
        return individualUser;
    }

}
