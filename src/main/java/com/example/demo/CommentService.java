package com.example.demo;

import com.example.demo.model.Article;
import com.example.demo.model.Comment;
import com.example.demo.repo.ArticleRepository;
import com.example.demo.repo.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final ArticleRepository articleRepo;
    private final CommentRepository commentRepo;
    public CommentService(ArticleRepository articleRepo, CommentRepository commentRepo) {
        this.articleRepo = articleRepo;
        this.commentRepo = commentRepo;
    }

    //CREATE
    public Comment create(Long articleId, Long id, String content,String password) {
        Article article = articleRepo.findById(articleId)
                .orElseThrow(null);
        Comment comment = new Comment(content, password,article);
        return commentRepo.save(comment);
    }
    //READONE
    public Comment readOne(Long articleId,Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(null);
        if(!comment.getArticle().getId().equals(articleId)) {
            return null;
        }
        return comment;
    }

}
