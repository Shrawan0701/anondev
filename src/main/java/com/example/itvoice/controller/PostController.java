package com.example.itvoice.controller;

import com.example.itvoice.model.Post;
import com.example.itvoice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return service.addPost(post);
    }

    @GetMapping
    public List<Post> getPosts(@RequestParam(defaultValue = "popular") String sort) {
        return service.getPostsSorted(sort);
    }

    @GetMapping("/company/{company}")
    public List<Post> getPostsByCompany(@PathVariable String company) {
        return service.getPostsByCompany(company);
    }

    @PutMapping("/{id}/upvote")
    public Post upvote(@PathVariable Long id) {
        return service.upvote(id);
    }

    @PutMapping("/{id}/downvote")
    public Post downvote(@PathVariable Long id) {
        return service.downvote(id);
    }

    @PutMapping("/{id}/report")
    public Post report(@PathVariable Long id) {
        return service.report(id);
    }
}
