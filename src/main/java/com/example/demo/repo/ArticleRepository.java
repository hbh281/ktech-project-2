package com.example.demo.repo;

import com.example.demo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository
        extends JpaRepository<Article, Long> {}

