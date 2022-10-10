package com.techgen.blog.controller;

import com.techgen.blog.model.CommentDTO;
import com.techgen.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CommentController exposes CRUD API's for Comment resources")
@RestController
@RequestMapping("/v1/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "API to create comment for particular postId")
    @PostMapping("/posts/{post-id}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "post-id") long postId, @Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(postId, commentDTO));
    }

    @ApiOperation(value = "API to get all comments for particular postId")
    @GetMapping("/posts/{post-id}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable(value = "post-id") long postId) {
        return ResponseEntity.ok().body(commentService.getCommentsByPostId(postId));
    }

    @ApiOperation(value = "API to get specific comment for particular postId")
    @GetMapping("/posts/{post-id}/comments/{comment-id}")
    public ResponseEntity<CommentDTO> getCommentsById(@PathVariable(value = "post-id") long postId,
                                                      @PathVariable(value = "comment-id") long commentId) {
        return ResponseEntity.ok().body(commentService.getCommentsById(postId, commentId));
    }

    @ApiOperation(value = "API to update specific comment for particular postId")
    @PutMapping("/posts/{post-id}/comments/{comment-id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "post-id") long postId,
                                                    @PathVariable(value = "comment-id") long commentId, @Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(postId, commentId, commentDTO));
    }

    @ApiOperation(value = "API to delete specific comment for particular postId")
    @DeleteMapping("/posts/{post-id}/comments/{comment-id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "post-id") long postId,
                                                @PathVariable(value = "comment-id") long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
    }
}
