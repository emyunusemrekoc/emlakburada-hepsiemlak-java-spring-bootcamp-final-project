package com.example.emlakburadaadvertstatus.dto.request;


import com.example.emlakburadaadvertstatus.model.enums.AdvertStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertStatusRequest {


    private int id;
    private AdvertStatus advertStatus;

}
