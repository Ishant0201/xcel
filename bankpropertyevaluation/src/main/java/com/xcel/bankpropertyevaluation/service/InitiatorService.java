package com.xcel.bankpropertyevaluation.service;

import com.xcel.bankpropertyevaluation.dto.AddInitiatorRequest;
import com.xcel.bankpropertyevaluation.dto.AddInitiatorResponse;
import com.xcel.bankpropertyevaluation.dto.GetInitiatorByIdResponse;


public interface InitiatorService {

    GetInitiatorByIdResponse getInitiatorByInitatorBusinessUnit(String initatorBusinessUnit);

    /**
     * @param initiatorDetails
     * @return
     */
    AddInitiatorResponse addInitiatorDetails(AddInitiatorRequest initiatorDetails);
}
