package com.example.demo;

import com.example.demo.model.Article;
import com.example.demo.model.Comment;
import com.example.demo.repo.ArticleRepository;
import com.example.demo.repo.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final ArticleRepository articleRepo;
    private final CommentRepository commentRepo;
    public CommentService(ArticleRepository articleRepo, CommentRepository commentRepo) {
        this.articleRepo = articleRepo;
        this.commentRepo = commentRepo;
    }

    //CREATE
    public Comment create(Long articleId, String content,String password) {
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
    public List<Comment> readAll() {
        return commentRepo.findAll();
    }
    public void delete(Long articleId, Long commentId,String cPassword) {
        Comment comment = commentRepo.findByArticleIdAndId(articleId, commentId).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + commentId));

        if (cPassword.equals(comment.getPassword())) {
            commentRepo.delete(comment);
        }

    }

}
