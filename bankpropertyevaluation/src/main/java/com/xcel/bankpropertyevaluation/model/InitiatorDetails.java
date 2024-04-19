package com.xcel.bankpropertyevaluation.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class InitiatorDetails {

    private String initiatorName;
    private String initiatorBusinessUnit;
    private int initiatorContactNo;
}
