package com.techgen.blog.model;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
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

	private List<String> tags;
}
