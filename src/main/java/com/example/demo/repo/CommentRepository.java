package com.example.demo.repo;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {
    Optional<Comment> findByArticleIdAndId(Long articleId, Long commentId);
}
