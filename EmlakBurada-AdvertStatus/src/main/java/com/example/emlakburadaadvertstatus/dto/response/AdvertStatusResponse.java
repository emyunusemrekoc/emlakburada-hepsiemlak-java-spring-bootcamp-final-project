package com.example.emlakburadaadvertstatus.dto.response;

import com.example.emlakburadaadvertstatus.model.enums.AdvertStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertStatusResponse {

    private int id;
    private AdvertStatus advertStatus;
}
