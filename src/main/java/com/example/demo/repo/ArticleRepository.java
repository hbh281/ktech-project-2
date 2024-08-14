package com.example.demo.repo;

import com.example.demo.model.Article;
import com.example.demo.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository
        extends JpaRepository<Article, Long> {
    List<Article> findByBoardTypeOrderByIdDesc(Board boardType);
    List<Article> findAllByOrderByIdDesc();
    Optional<Article> findFirstByIdLessThanAndBoardType_IdOrderByIdDesc(Long id, Long boardId);
    Optional<Article> findFirstByIdGreaterThanAndBoardType_IdOrderByIdDesc(Long id, Long boardId);

    Optional<Article> findFirstByIdGreaterThanOrderByIdDesc(Long id);
    Optional<Article> findFirstByIdLessThanOrderByIdDesc(Long id);
}

