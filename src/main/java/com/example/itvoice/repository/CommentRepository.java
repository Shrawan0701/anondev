package com.example.itvoice.repository;

import com.example.itvoice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdOrderByTimestampDesc(Long postId);
}
