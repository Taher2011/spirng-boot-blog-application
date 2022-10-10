package com.techgen.blog.model;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(value = "Post model information")
@Data
@JsonInclude(Include.NON_NULL)
public class PostDTO {

	@ApiModelProperty(value = "Blog post id")
	private Long id;

	@ApiModelProperty(value = "Blog post title")
	@NotEmpty
	@Size(min = 2, message = "Post tittle should have atleast 2 character")
	private String title;

	@ApiModelProperty(value = "Blog post description")
	@NotEmpty
	@Size(min = 10, message = "Post tittle should have atleast 10 character")
	private String description;

	@ApiModelProperty(value = "Blog post content")
	@NotEmpty
	private String content;

	@ApiModelProperty(value = "Blog post comments")
	private Set<CommentDTO> comments;

	@ApiModelProperty(value = "Blog post tags")
	private List<String> tags;
}
