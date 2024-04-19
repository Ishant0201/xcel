package com.xcel.bankpropertyevaluation.repository;


import com.xcel.bankpropertyevaluation.model.PropertyEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyEvaluationRepository extends JpaRepository<PropertyEvaluation, Long> {

}
