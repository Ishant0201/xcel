package com.xcel.bankpropertyevaluation.dto;

import com.xcel.bankpropertyevaluation.model.PropertyEvaluation;
import lombok.Data;

import java.util.List;

@Data
public class GetAllPropertiesResponse {

    private List<PropertyEvaluation> propertyEvaluation;
}
