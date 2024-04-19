package com.xcel.bankpropertyevaluation.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class BorrowerDetails {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "customerNumber", column = @Column(name = "mainBorrower_customerNumber")),
            @AttributeOverride(name = "customerName", column = @Column(name = "mainBorrower_customerName")),
            @AttributeOverride(name = "contactNumber", column = @Column(name = "mainBorrower_contactNumber")),
            @AttributeOverride(name = "email", column = @Column(name = "mainBorrower_email")),
            @AttributeOverride(name = "address", column = @Column(name = "mainBorrower_address"))
    })
    private Borrower mainBorrower;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "customerNumber", column = @Column(name = "jointBorrowers_customerNumber")),
            @AttributeOverride(name = "customerName", column = @Column(name = "jointBorrowers_customerName")),
            @AttributeOverride(name = "contactNumber", column = @Column(name = "jointBorrowers_contactNumber")),
            @AttributeOverride(name = "email", column = @Column(name = "jointBorrowers_email")),
            @AttributeOverride(name = "address", column = @Column(name = "jointBorrowers_address"))
    })
    private Borrower jointBorrowers;
}
