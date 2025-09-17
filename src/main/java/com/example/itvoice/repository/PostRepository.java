package com.example.itvoice.repository;

import com.example.itvoice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCompanyIgnoreCaseOrderByTimestampDesc(String company);

    @Query("SELECT p FROM Post p ORDER BY (p.upvotes - p.downvotes) DESC")
    List<Post> findPopularPosts();

    @Query("SELECT p FROM Post p ORDER BY p.timestamp DESC")
    List<Post> findRecentPosts();
}
