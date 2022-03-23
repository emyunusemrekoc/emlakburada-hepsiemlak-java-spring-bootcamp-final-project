package com.example.emlakburadaadvert.dto.request;

import com.example.emlakburadaadvert.model.enums.AdvertStatus;
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
