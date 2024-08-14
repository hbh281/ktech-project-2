package com.example.demo;

import com.example.demo.model.Board;
import com.example.demo.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepo;
    public BoardService(BoardRepository boardRepo) {
        this.boardRepo = boardRepo;
    }
    public List<Board> readAll() {
        return boardRepo.findAll();
    }
    public Board readOne(Long id) {
        return boardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }
}
