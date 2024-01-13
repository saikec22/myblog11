package com.myblog11.payload;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    private List<PostDto> postDtos;
    private int pageSize;
    private int pageNo;
    private int totalPage;
    private long totalElements;
    private boolean isLast;
}
