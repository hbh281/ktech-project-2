package com.example.demo.repo;

import com.example.demo.model.Article;
import com.example.demo.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository
        extends JpaRepository<Article, Long> {
    List<Article> findByBoardTypeOrderByIdDesc(Board boardType);
    List<Article> findAllByOrderByIdDesc();
}

