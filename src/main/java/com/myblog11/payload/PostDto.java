package com.myblog11.payload;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;

    @NotEmpty@NotNull
    @Size(min = 3,message = "title should be atlead 3 character")
    private String title;
    @NotEmpty@NotNull
    @Size(min = 4,message = "discription should be atlead 3 character")
    private String description;
    @NotEmpty@NotNull
    @Size(min = 5,message = "content should be atlead 3 character")
    private String content;
}
