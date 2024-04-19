package com.xcel.bankpropertyevaluation.service.impl;

import com.xcel.bankpropertyevaluation.model.Comments;
import com.xcel.bankpropertyevaluation.repository.CommentRepository;
import com.xcel.bankpropertyevaluation.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comments> getCommentsByinitiatorBusinessUnit(String initiatorBusinessUnit) {
        var res = commentRepository.findAllByInitiatorBusinessUnit(initiatorBusinessUnit);

        log.info("CommentService >> getCommentsByinitiatorBusinessUnit dbRes: {}", res);
        return res;
    }

}
