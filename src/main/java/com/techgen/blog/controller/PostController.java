package com.techgen.blog.controller;

import com.techgen.blog.model.PostDTO;
import com.techgen.blog.model.PostResponse;
import com.techgen.blog.service.PostService;
import com.techgen.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')") // Role based authorization i.e. only user with ADMIN role can access
    @PostMapping("")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDTO));
    }

    @GetMapping("")
    public ResponseEntity<PostResponse> getPosts(@RequestParam(value = "page-no", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                 @RequestParam(value = "page-size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                 @RequestParam(value = "sort-by", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                 @RequestParam(value = "sort-dir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok().body(postService.getPosts(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(value = "post-id") long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @PreAuthorize("hasRole('ADMIN')") // Role based authorization i.e. only user with ADMIN role can access
    @PutMapping("/{post-id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @PathVariable(value = "post-id") long id, @RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.updatePost(id, postDTO));
    }

    @PreAuthorize("hasRole('ADMIN')") // Role based authorization i.e. only user with ADMIN role can access
    @DeleteMapping("/{post-id}")
    public ResponseEntity<String> deletePostById(@PathVariable(value = "post-id") long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok().body("post " + id + " deleted successfully");
    }
}
