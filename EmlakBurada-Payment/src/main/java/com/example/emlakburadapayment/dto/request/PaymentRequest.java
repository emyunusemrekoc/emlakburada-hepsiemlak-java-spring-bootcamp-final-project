package com.example.emlakburadapayment.dto.request;



import com.example.emlakburadapayment.model.enums.CardType;
import com.example.emlakburadapayment.model.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    @JsonIgnore
    private int id;
    private Double amount;

}
