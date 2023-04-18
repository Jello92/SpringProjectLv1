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
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if (boardRepository.findByUserPw(requestDto.getPassword()) !=null) {
            board.update(requestDto);
            return board.getId();
        } else return null;
    }

    @Transactional
    public Long deleteBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if (boardRepository.findByUserPw(requestDto.getPassword()) !=null) {
            boardRepository.deleteById(id);
            return id;
        } else return null;
    }
}
