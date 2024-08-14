package com.example.demo;


import com.example.demo.model.Article;
import com.example.demo.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleController {
    private final ArticleService service;
    private final BoardService boardService;
    public ArticleController(ArticleService service,BoardService boardService) {
        this.service = service;
        this.boardService = boardService;
    }
    // CREATE
    @GetMapping("/create")
    public String createView(Model model) {
        List<Board> boards = boardService.readAll();
        model.addAttribute("boards", boards);
        return "article/create.html";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("boardId")
            Long boardId
    ) {

        Long id = service.create(title, content, password, boardId).getId();
        // POST - redirect - GET
        return String.format("redirect:/article/%d", id);
    }

    // READ ALL
    @GetMapping  // /articles
    public String readAll(Model model) {
        model.addAttribute("article", service.readAll());
        return "board/board.html";
    }
    //READ ONE
    @GetMapping("{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        Article article = service.readOne(id);
        Long boardId = article.getBoardType().getId();
        Article nextArticle = service.findNext(id,boardId);
        Article prevArticle = service.findPrev(id,boardId);


        model.addAttribute("nextArticle",nextArticle);
        model.addAttribute("prevArticle", prevArticle);
        model.addAttribute("article", service.readOne(id));
        return "article/read.html";
    }
    //UPDATE
    @GetMapping ("{id}/update")
    public String updateView(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        List<Board> boards = boardService.readAll();
        model.addAttribute("boards", boards);
        model.addAttribute("article", service.readOne(id));
        return "article/update.html";
    }
    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("boardId")
            Long boardId
    ){

        service.update(id, title, content, password,boardId);
        return String.format("redirect:/article/%d", id);
    }
    //DELETE
    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id,
            @RequestParam("password")
            String password
    ){
        service.delete(id,password);
        return String.format("redirect:/article/%d", id);
    }
}
