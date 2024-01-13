package com.myblog11.service.impl;

import com.myblog11.entity.Post;
import com.myblog11.exception.ResourceNotFound;
import com.myblog11.payload.PostDto;
import com.myblog11.payload.ResponseDto;
import com.myblog11.repository.PostRepository;
import com.myblog11.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=mapToEntity(postDto);
        Post postSaved = postRepo.save(post);
        PostDto dto=mapToDto(postSaved);
        return dto;
    }

    private PostDto mapToDto(Post postSaved) {
        PostDto postdto = new PostDto();
        postdto.setId(postSaved.getId());
        postdto.setTitle(postSaved.getTitle());
        postdto.setDescription(postSaved.getDescription());
        postdto.setContent(postSaved.getContent());
        return postdto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    @Override
    public void deletePost(long id) {
            postRepo.findById(id).orElseThrow(
                    ()-> new ResourceNotFound("post id not found"+id)
            );
            postRepo.deleteById(id);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post=postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("id not found"+id)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post save = postRepo.save(post);
        PostDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("post id not found"+id)
        );
        PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    public ResponseDto getAllPost(int pageSize, int pageNo, String sortDir, String sortBy) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                    Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable =PageRequest.of(pageNo,pageSize,sort);

        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        ResponseDto pd = new ResponseDto();
        pd.setPostDtos(dtos);
        pd.setPageSize(pagePost.getSize());
        pd.setPageNo(pagePost.getNumber());
        pd.setTotalPage(pagePost.getTotalPages());
        pd.setTotalElements(pagePost.getTotalElements());
        pd.setLast(pagePost.isLast());
        return pd;
    }
}
