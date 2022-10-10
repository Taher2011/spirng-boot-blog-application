package com.techgen.blog.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Api(value = "Comment model information")
@Data
public class CommentDTO {

    @ApiModelProperty(value = "Blog comment id")
    private long id;

    @ApiModelProperty(value = "Blog comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @ApiModelProperty(value = "Blog comment email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    @ApiModelProperty(value = "Blog comment body")
    @NotEmpty
    @Size(min = 10, message = "Comment body should have minimum 10 characters")
    private String body;
}
