package com.myblog11.service;


import com.myblog11.payload.PostDto;
import com.myblog11.payload.ResponseDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    void deletePost(long id);
    PostDto updatePost(long id,PostDto postDto);
    PostDto getPostById(long id);
    ResponseDto getAllPost(int pageSize, int pageNo, String sortDir, String sortBy);
}
