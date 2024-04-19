package com.xcel.bankpropertyevaluation.service;

import com.xcel.bankpropertyevaluation.model.Comments;

import java.util.List;

public interface CommentService {

    List<Comments> getCommentsByinitiatorBusinessUnit(String initiatorBusinessUnit);
}
