package com.myblog11.controller;

import com.myblog11.payload.CommentDto;
import com.myblog11.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId,
                                                        @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("/posts/{id}")
    public List<CommentDto> getCommentByPostId(@PathVariable long id){
        List<CommentDto> dtos=commentService.getCommentByPostId(id);
        return dtos;
    }
}
