package com.xcel.bankpropertyevaluation.model;

import com.xcel.bankpropertyevaluation.enums.Currency;
import com.xcel.bankpropertyevaluation.enums.Purpose;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Embeddable
public class FacilityDetails {
    @NotEmpty(message = "Must not be Null or Empty")
    private String typeOfFacility;

    @NotEmpty(message = "Must not be Null or Empty")
    private String category;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @NotEmpty(message = "Must not be Null or Empty")
    @Positive(message = "Must be a Positive Number")
    private int termInMonths;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotEmpty(message = "Must not be Null or Empty")
    @Positive(message = "Must be a Positive Number")
    private double amount;
}
