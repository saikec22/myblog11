package com.myblog11.service.impl;

import com.myblog11.entity.Comment;
import com.myblog11.entity.Post;
import com.myblog11.exception.ResourceNotFound;
import com.myblog11.payload.CommentDto;
import com.myblog11.repository.CommentRepository;
import com.myblog11.repository.PostRepository;
import com.myblog11.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post=postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFound("postId not found"+postId)
        );

        Comment comment=mapToEntity(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);

        CommentDto dto=mapToDto(save);

        return dto;
    }

    private CommentDto mapToDto(Comment save) {
        CommentDto commentDto=modelMapper.map(save,CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment=modelMapper.map(commentDto,Comment.class);
        return comment;
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFound("post id not found"+postId)
        );
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public CommentDto getCommentById(long commentId) {
        return null;
    }

    @Override
    public List<CommentDto> getAllComment() {
        return null;
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {

    }
}
