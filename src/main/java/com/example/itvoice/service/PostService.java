package com.example.itvoice.service;

import com.example.itvoice.model.Post;
import com.example.itvoice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repo;

    public PostService(PostRepository repo) {
        this.repo = repo;
    }

    public Post addPost(Post post) {
        return repo.save(post);
    }

    public List<Post> getPostsSorted(String sort) {
        List<Post> posts = repo.findAll();
        if ("recent".equalsIgnoreCase(sort)) {
            posts.sort(Comparator.comparing(Post::getTimestamp).reversed());
        } else {
            posts.sort((a, b) -> (b.getUpvotes() - b.getDownvotes()) - (a.getUpvotes() - a.getDownvotes()));
        }
        return posts;
    }

    public List<Post> getPostsByCompany(String company) {
        return repo.findByCompanyIgnoreCaseOrderByTimestampDesc(company);
    }

    public Post upvote(Long id) {
        Post post = repo.findById(id).orElseThrow();
        post.setUpvotes(post.getUpvotes() + 1);
        return repo.save(post);
    }

    public Post downvote(Long id) {
        Post post = repo.findById(id).orElseThrow();
        post.setDownvotes(post.getDownvotes() + 1);
        return repo.save(post);
    }

    public Post report(Long id) {
        Post post = repo.findById(id).orElseThrow();
        post.setReportCount(post.getReportCount() + 1);
        return repo.save(post);
    }
}
