package com.example.emlakburadapurchase.dto.request;

import com.example.emlakburadapurchase.model.enums.PurchaseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseStatusRequest {

    @JsonIgnore
    private int id;
    private PurchaseStatus purchaseStatus;


}
