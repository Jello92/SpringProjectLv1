package com.sparta.board_spring.repository;

import com.sparta.board_spring.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository <Board, Long>{
    List<Board> findAllByOrderByModifiedAtDesc();

}
