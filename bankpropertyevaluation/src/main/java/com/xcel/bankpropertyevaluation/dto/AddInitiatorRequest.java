package com.xcel.bankpropertyevaluation.dto;

import lombok.Data;

@Data
public class AddInitiatorRequest {

    private String initiatorName;
    private String initiatorBusinessUnit;
    private int initiatorContactNo;
    private String password;
}
