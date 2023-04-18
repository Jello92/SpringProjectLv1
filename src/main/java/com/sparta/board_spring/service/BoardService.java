package com.sparta.board_spring.service;

import com.sparta.board_spring.dto.BoardRequestDto;
import com.sparta.board_spring.entity.Board;
import com.sparta.board_spring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoard(BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return board;

}
    @Transactional(readOnly = true)
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Board update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Board board1 = boardRepository.findByIdAndPassword(id, requestDto.getPassword()).orElseThrow(
                ()-> new IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        );
        board.update(requestDto);
        return board;
    }

    @Transactional
    public String deleteBoard(Long id, String requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        Board board1 = boardRepository.findByIdAndPassword(id, board.getPassword()).orElseThrow(
                ()-> new IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        );
        boardRepository.deleteByIdAndPassword(id, board.getPassword());
        return "Delete Success";
    }
}
