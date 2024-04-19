package com.xcel.bankpropertyevaluation.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AddPropertyEvaluationResponse {

    private String refrenceNo;
    private String createdBy;
    private Date createdOn;
}
