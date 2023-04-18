package com.sparta.board_spring.repository;

import com.sparta.board_spring.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository <Board, Long>{
    List<Board> findAllByOrderByModifiedAtDesc();
    Optional<Board> findByIdAndPassword(Long id, String password);
    Optional<Board> deleteByIdAndPassword(Long id, String password);

}
