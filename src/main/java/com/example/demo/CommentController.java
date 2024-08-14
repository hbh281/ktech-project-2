package com.example.demo;

import com.example.demo.model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("article/{articleId}/comments")
public class CommentController {
    private final CommentService service;
    public CommentController(CommentService service) {
        this.service = service;
    }

    // CREATE
    // Comment는 Article에 종속되어 있으므로 URL 하나만 만들면 된다.
    // /articles/{articleId}/comments/create
    @PostMapping("create")
    public String create(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("content")
            String content,
            @RequestParam("cPassword")
            String cPassword
    ) {
        service.create(articleId, content, cPassword);
        // POST - redirect - GET
        return String.format("redirect:/article/%d", articleId);
    }
//    @GetMapping("{id}")
//    public String readOne(@PathVariable("articleId")
//                           Long articleId,
//                           @PathVariable("id")
//                           Long commentId,
//                           Model model){
//        model.addAttribute("article", service.readOne(articleId,commentId));;
//        return "cRead.html";
//    }
    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable("articleId") Long articleId,
            @PathVariable("id") Long id,  // This is the comment ID
            @RequestParam("cPassword")
            String cPassword,
            Model model
    ) {

        service.delete(articleId, id, cPassword);
        return String.format("redirect:/article/%d", articleId);

    }
}
