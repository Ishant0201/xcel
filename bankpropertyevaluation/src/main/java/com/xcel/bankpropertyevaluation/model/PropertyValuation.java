package com.xcel.bankpropertyevaluation.model;

import com.xcel.bankpropertyevaluation.enums.TypeOfEvaluation;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
public class PropertyValuation {

    private String fosReference;

    @Enumerated(EnumType.STRING)
    private TypeOfEvaluation typeOfEvaluation;
}
