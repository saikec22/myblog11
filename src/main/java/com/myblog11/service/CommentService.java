package com.myblog11.service;

import com.myblog11.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long commentId);

    List<CommentDto> getAllComment();

    void deleteCommentById(long postId,long commentId);

}
