package com.example.emlakburadapurchase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalanceIdentificationRequest {

    private int id;
    private int userId;
    private int advertBalance;
    private int additionalDayEndDateOfPackage;
}
