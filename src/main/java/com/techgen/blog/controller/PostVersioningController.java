package com.techgen.blog.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techgen.blog.model.PostDTO;
import com.techgen.blog.service.PostService;

@RestController
@RequestMapping("/api/version/posts")
public class PostVersioningController {

	private final PostService postService;

	public PostVersioningController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(value = "/v1/{post-id}") // this is versioning using URI
	public ResponseEntity<PostDTO> getPostByIdUsingURIVersioning(@PathVariable(value = "post-id") long id) {
		PostDTO post = postService.getPostById(id);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping(value = "/v2/{post-id}") // this is versioning using URI
	public ResponseEntity<PostDTO> getPostTagsByIdUsingURIVersioning(@PathVariable(value = "post-id") long id) {
		PostDTO post = postService.getPostById(id);
		List<String> tags = Arrays.asList("Java", "C++");
		post.setTags(tags);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping(value = "/{post-id}", params = "version=1") // this is versioning using Request param
	public ResponseEntity<PostDTO> getPostByIdUsingReqParamVersioning(@PathVariable(value = "post-id") long id) {
		PostDTO post = postService.getPostById(id);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping(value = "/{post-id}", params = "version=2") // this is versioning using Request param
	public ResponseEntity<PostDTO> getPostTagsByIdUsingReqParamVersioning(@PathVariable(value = "post-id") long id) {
		PostDTO post = postService.getPostById(id);
		List<String> tags = Arrays.asList("Java", "C++");
		post.setTags(tags);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping(value = "/{post-id}", headers = "API-VERSION=1") // this is versioning using Request header
	public ResponseEntity<PostDTO> getPostByIdUsingReqHeaderVersioning(@PathVariable(value = "post-id") long id) {
		PostDTO post = postService.getPostById(id);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping(value = "/{post-id}", headers = "API-VERSION=2") // this is versioning using Request param
	public ResponseEntity<PostDTO> getPostTagsByIdUsingReqHeaderVersioning(@PathVariable(value = "post-id") long id) {
		PostDTO post = postService.getPostById(id);
		List<String> tags = Arrays.asList("Java", "C++");
		post.setTags(tags);
		return ResponseEntity.ok().body(post);
	}

}
