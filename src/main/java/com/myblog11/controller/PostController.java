package com.myblog11.controller;

import com.myblog11.payload.PostDto;
import com.myblog11.payload.ResponseDto;
import com.myblog11.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post is deleted ",HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id,@RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseDto getAllPost(
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy
    ){
          ResponseDto dtos=postService.getAllPost(pageSize,pageNo,sortDir,sortBy);
         return dtos;
    }

}
