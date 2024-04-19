package com.xcel.bankpropertyevaluation.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class PropertyEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private InitiatorDetails initiatorDetails;

    private String refrenceNo;
    private Date receivedOn;
    private Date createdOn;
    private Date modifiedOn;
    private String createdBy;

    @Embedded
    private FacilityDetails facilityDetails;
    @Embedded
    private PropertyValuation propertyValuation;
    @Embedded
    private BorrowerDetails borrowerDetails;
}
