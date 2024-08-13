package com.example.demo;

import com.example.demo.model.Article;
import com.example.demo.model.Board;
import com.example.demo.repo.ArticleRepository;
import com.example.demo.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository repository;
    private final BoardRepository boardRepository;
    public ArticleService(ArticleRepository repository,BoardRepository boardRepository) {
        this.repository = repository;
        this.boardRepository = boardRepository;
    }

    //Create
    public Article create(String title, String content, String password, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(null);
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setPassword(password);
        article.setBoardType(board);
        return repository.save(article);
    }
    // Read All
    public List<Article> readAll() {
        return repository.findAll();
    }

    // Read One
    public Article readOne(Long id) {
        return repository.findById(id)
                .orElse(null);
    }
    //update
    public Article update(Long id,
                          String title,
                          String content,
                          String password,
                          Long boardId
                          ) {
        Optional<Article> optionalTarget = repository.findById(id);
        if (optionalTarget.isEmpty()) {
            return null;
        }
        Article target = optionalTarget.get();
        if (password.equals(optionalTarget.get().getPassword())) {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(null);
            target.setTitle(title);
            target.setContent(content);
            target.setBoardType(board);
        }
        return repository.save(target);
    }

    // Delete
    public void delete(Long id,String password) {

        if (password.equals(repository.findById(id).get().getPassword())) {
            repository.deleteById(id);
        }else {
            throw new RuntimeException("wrong pass!");
        }
    }
}
