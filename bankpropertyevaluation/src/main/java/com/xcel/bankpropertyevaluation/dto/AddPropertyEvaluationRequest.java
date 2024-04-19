package com.xcel.bankpropertyevaluation.dto;

import com.xcel.bankpropertyevaluation.model.BorrowerDetails;
import com.xcel.bankpropertyevaluation.model.FacilityDetails;
import com.xcel.bankpropertyevaluation.model.PropertyValuation;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class AddPropertyEvaluationRequest {

    @Embedded
    private FacilityDetails facilityDetails;


    @Embedded
    private PropertyValuation propertyValuation;


    @Embedded
    private BorrowerDetails borrowerDetails;

    private String comment;

}
