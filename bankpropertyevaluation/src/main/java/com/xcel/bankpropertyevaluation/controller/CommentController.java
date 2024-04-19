package com.xcel.bankpropertyevaluation.controller;

import com.xcel.bankpropertyevaluation.model.Comments;
import com.xcel.bankpropertyevaluation.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public List<Comments> getCommentsByUser(@RequestParam String initiatorBusinessUnit) {
        return commentService.getCommentsByinitiatorBusinessUnit(initiatorBusinessUnit);
    }

}
