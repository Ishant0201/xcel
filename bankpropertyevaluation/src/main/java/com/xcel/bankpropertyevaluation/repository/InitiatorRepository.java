package com.xcel.bankpropertyevaluation.repository;

import com.xcel.bankpropertyevaluation.model.Initiator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitiatorRepository extends JpaRepository<Initiator, Long> {

    Initiator findByInitiatorBusinessUnit(String initiatorBusinessUnit);
}
