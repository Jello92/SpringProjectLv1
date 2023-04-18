package com.sparta.board_spring.controller;

import com.sparta.board_spring.dto.BoardRequestDto;
import com.sparta.board_spring.entity.Board;
import com.sparta.board_spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public ModelAndView home (Board board){
        return new ModelAndView("index.html");
    }


    @PostMapping("/api/boards")
    public Board createBoard (@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/api/boards")
    public List<Board> getBoard(){
        return boardService.getBoard();
    }

    @PutMapping("/api/boards/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.update(id, requestDto);
    }

    @DeleteMapping("/api/baords/{id}")
    public String deleteBoard (@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto.getPassword());
    }
}
