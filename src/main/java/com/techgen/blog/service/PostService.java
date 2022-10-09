package com.techgen.blog.service;

import com.techgen.blog.model.PostDTO;
import com.techgen.blog.model.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(long id, PostDTO postDTO);

    void deletePostById(long id);
}
