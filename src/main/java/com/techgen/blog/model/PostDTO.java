package com.techgen.blog.model;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post tittle should have atleast 2 character")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post tittle should have atleast 10 character")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDTO> comments;
}
