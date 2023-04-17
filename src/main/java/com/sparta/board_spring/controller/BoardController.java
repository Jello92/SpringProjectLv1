package com.sparta.board_spring.controller;

import com.sparta.board_spring.dto.BoardRequestDto;
import com.sparta.board_spring.dto.BoardResponseDto;
import com.sparta.board_spring.entity.Board;
import com.sparta.board_spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/main")
    public List<BoardResponseDto> getBoardList(){
        List<Board> boards = boardService.getBoard();
        return boards.stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/post")
    public String createBoard(@RequestBody BoardRequestDto requestDto) {
        boardService.createBoard(requestDto);

        return "게시글 저장에 성공했습니다.";

    }

    @GetMapping("/list")
    public List<BoardResponseDto> getBoard() {
        List<Board> boards = boardService.getBoard();
        return boards.stream() //stream 공부
                .map(BoardResponseDto::new)
                .collect(Collectors.toList()); // for문 사용 -> 해당 방법으로 간결하게 해결 가능. 특강 참고 및 Collectors 공부
    }

    @PutMapping("/update/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        Board board = boardService.getBoardById(id);
        if (board == null){
            return "게시글이 존재하지 않습니다.";
        } else if (!board.getPassword().equals(requestDto.getPassword())){
            return "잘못된 비밀번호입니다.";
        } else {
            board.update(requestDto);
            boardService.updateBoard(id, requestDto);
            return "게시글 업데이트에 성공했습니다.";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        Board board = boardService.getBoardById(id);
        if(board == null){
            return "게시글이 존재하지 않습니다.";
        } else if(!board.getPassword().equals(requestDto.getPassword())){
            return "잘못된 비밀번호입니다.";
        } else {
            boardService.deleteBoard(id);
            return "게시글 삭제에 성공했습니다.";
        }
    }


}
