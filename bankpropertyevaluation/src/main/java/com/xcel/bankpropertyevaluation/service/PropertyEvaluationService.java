package com.xcel.bankpropertyevaluation.service;

import com.xcel.bankpropertyevaluation.dto.AddPropertyEvaluationRequest;
import com.xcel.bankpropertyevaluation.dto.AddPropertyEvaluationResponse;
import com.xcel.bankpropertyevaluation.dto.GetAllPropertiesResponse;
import org.springframework.stereotype.Service;

@Service
public interface PropertyEvaluationService {
    AddPropertyEvaluationResponse addPropertyEvaluation(AddPropertyEvaluationRequest propertyEvaluationRequest, String user);

    GetAllPropertiesResponse getAllPropertiesEvaluation(String initatorName, String initatorBusinessUnit);

}
