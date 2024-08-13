package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String password;

    @Setter
    @ManyToOne
    private Board boardType;

    @Setter
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    public Article() {}
    public Article(String title, String content, String password,Board boardType) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.boardType = boardType;
    }
}
