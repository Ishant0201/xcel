package com.xcel.bankpropertyevaluation.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Borrower {
    private String customerNumber;
    private String customerName;
    private Long contactNumber;
    private String email;
    private String address;
}
