package com.example.demo;

import com.example.demo.model.Article;
import com.example.demo.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("board")
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    public BoardController(BoardService boardService, ArticleService articleService) {
        this.boardService = boardService;
        this.articleService = articleService;
    }
    @GetMapping
    public String readAll(Model model) {
        List<Article> articles = articleService.readAll();
        model.addAttribute("articles", articles);
        model.addAttribute("boards",boardService.readAll());
        return "board/board.html";
    }
    @GetMapping("{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
    ){
        Board board = boardService.readOne(id);
        List<Article> articles = articleService.findByBoardTypeOrderByIdDesc(board);
        model.addAttribute("articles",articles);
        model.addAttribute("board",board);

        return "board/boardread.html";
    }
}
