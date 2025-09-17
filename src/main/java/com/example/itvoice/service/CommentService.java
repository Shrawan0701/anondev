package com.example.itvoice.service;

import com.example.itvoice.model.Comment;
import com.example.itvoice.model.Post;
import com.example.itvoice.repository.CommentRepository;
import com.example.itvoice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    public CommentService(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepo.findByPostIdOrderByTimestampDesc(postId);
    }

    public Comment addComment(Long postId, Comment comment) {
        Post post = postRepo.findById(postId).orElseThrow();
        comment.setPost(post);
        return commentRepo.save(comment);
    }

    public Comment upvote(Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow();
        comment.setUpvotes(comment.getUpvotes() + 1);
        return commentRepo.save(comment);
    }

    public Comment downvote(Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow();
        comment.setDownvotes(comment.getDownvotes() + 1);
        return commentRepo.save(comment);
    }

}
