package com.xcel.bankpropertyevaluation.dto;

import com.xcel.bankpropertyevaluation.model.Initiator;
import com.xcel.bankpropertyevaluation.model.InitiatorDetails;
import com.xcel.bankpropertyevaluation.model.PropertyEvaluation;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public PropertyEvaluation mapToEntity(AddPropertyEvaluationRequest request) {
        PropertyEvaluation entity = new PropertyEvaluation();
        entity.setFacilityDetails(request.getFacilityDetails());
        entity.setPropertyValuation(request.getPropertyValuation());
        entity.setBorrowerDetails(request.getBorrowerDetails());
        return entity;
    }

    public GetInitiatorByIdResponse mapToResponse(Initiator request) {
        GetInitiatorByIdResponse response = new GetInitiatorByIdResponse();
        response.setInitiatorName(request.getInitiatorName());
        response.setInitiatorBusinessUnit(request.getInitiatorBusinessUnit());
        response.setInitiatorContactNo(request.getInitiatorContactNo());
        return response;
    }

    public InitiatorDetails mapToinitiatorDetails(GetInitiatorByIdResponse initiatorDetails) {
        InitiatorDetails details = new InitiatorDetails();
        details.setInitiatorBusinessUnit(initiatorDetails.getInitiatorBusinessUnit());
        details.setInitiatorContactNo(initiatorDetails.getInitiatorContactNo());
        details.setInitiatorName(initiatorDetails.getInitiatorName());
        return details;
    }

    public Initiator mapToinitiator(AddInitiatorRequest initiatorDetails) {
        Initiator initiator = new Initiator();
        initiator.setInitiatorBusinessUnit(initiatorDetails.getInitiatorBusinessUnit());
        initiator.setInitiatorContactNo(initiatorDetails.getInitiatorContactNo());
        initiator.setInitiatorName(initiatorDetails.getInitiatorName());
        initiator.setPassword(initiatorDetails.getPassword());
        return initiator;
    }
}
