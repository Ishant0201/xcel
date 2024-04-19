package com.xcel.bankpropertyevaluation.repository;

import com.xcel.bankpropertyevaluation.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    Comments findByInitiatorBusinessUnit(String initiatorBusinessUnit);

    List<Comments> findAllByInitiatorBusinessUnit(String initiatorBusinessUnit);
}
