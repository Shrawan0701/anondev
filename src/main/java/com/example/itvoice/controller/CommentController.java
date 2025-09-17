package com.example.itvoice.controller;

import com.example.itvoice.model.Comment;
import com.example.itvoice.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return service.getCommentsByPost(postId);
    }

    @PostMapping("/{postId}")
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return service.addComment(postId, comment);
    }

    @PutMapping("/{id}/upvote")
    public Comment upvote(@PathVariable Long id) {
        return service.upvote(id);
    }

    @PutMapping("/{id}/downvote")
    public Comment downvote(@PathVariable Long id) {
        return service.downvote(id);
    }

}
