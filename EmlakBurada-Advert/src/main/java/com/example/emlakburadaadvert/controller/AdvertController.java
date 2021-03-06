package com.example.emlakburadaadvert.controller;

import com.example.emlakburadaadvert.dto.request.AdvertRequest;
import com.example.emlakburadaadvert.dto.request.AdvertUpdateRequest;
import com.example.emlakburadaadvert.dto.response.AdvertResponse;
import com.example.emlakburadaadvert.service.AdvertService;
import com.example.emlakburadaadvert.utils.results.DataResult;
import com.example.emlakburadaadvert.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertController {


    private AdvertService advertService;


    @Autowired
    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping("/adverts")
    public ResponseEntity<DataResult<List<AdvertResponse>>> findAll() {
        DataResult<List<AdvertResponse>> result= advertService.findAll();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    //login yapan kullanıcının id sine göre ilan ekleme
    @PostMapping("/adverts")
    public ResponseEntity<Result> add(@RequestBody AdvertRequest advertRequest,@RequestHeader(value = "id") int id) {
        Result result = advertService.add(advertRequest,id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    //id ye göre ilan güncelleme
    @PutMapping("/adverts/{id}")
    public ResponseEntity<Result> updateById(@PathVariable int id, @RequestBody AdvertUpdateRequest advertUpdateRequest){
        Result result = advertService.updateById(id,advertUpdateRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    // id ye göre ilan silme
    @DeleteMapping("/adverts/{id}")
    public ResponseEntity<Result> deleteById(@PathVariable int id){
        Result result = advertService.deleteById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    // id ye göre ilan bulma
    @GetMapping("/adverts/{id}")
    public ResponseEntity<DataResult<AdvertResponse>> findById(@PathVariable int id) {
        DataResult<AdvertResponse> result = advertService.findById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    //kullanıcı id sine göre aktif ilanları getirme

    @GetMapping("/adverts/actives/individual-user/{userId}")
    public ResponseEntity<DataResult<List<AdvertResponse>>> findAllByUserIdAndIsActive(  @PathVariable int userId) {
        DataResult<List<AdvertResponse>> result= advertService.findAllByUserIdAndIsActive(userId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    //kullanıcı id sine göre pasif ilanları getirme
    @GetMapping("/adverts/passives/individual-user/{userId}")
    public ResponseEntity<DataResult<List<AdvertResponse>>> findAllByUserIdAndIsPassive( @PathVariable  int userId) {

        DataResult<List<AdvertResponse>> result= advertService.findAllByUserIdAndIsPassive(userId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    //idye göre ilan statüsünü aktif duruma getirme
    @PutMapping("/adverts/update-status-active/{id}")
    public ResponseEntity<Result> updateStatusActiveById(@PathVariable int id){
        Result result = advertService.updateStatusActiveById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    //idye göre ilan statüsünü pasif duruma getirme
    @PutMapping("/adverts/update-status-passive/{id}")
    public ResponseEntity<Result> updateStatusPassiveById(@PathVariable int id){
        Result result = advertService.updateStatusPassiveById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


}
