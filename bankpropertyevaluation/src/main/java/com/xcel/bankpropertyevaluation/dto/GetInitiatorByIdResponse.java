package com.xcel.bankpropertyevaluation.dto;

import lombok.Data;

@Data
public class GetInitiatorByIdResponse {

    private String initiatorName;
    private String initiatorBusinessUnit;
    private int initiatorContactNo;

}
