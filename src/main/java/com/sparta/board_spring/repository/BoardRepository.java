package com.sparta.board_spring.repository;

import com.sparta.board_spring.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository <Board, Long>{
    // JpaRepository is an interface in Spring Data JPA that provides basic CRUD (create, read, update,delete) operation for the entity type specified as its first generic parameter
    // 즉, JpaRepository는 첫 번째 일반 매개 변수(Board)로 지정된 엔티티 유형에 대한 기본 CRUD 작업을 제공하고 엔티티의 ID 유형을 두번쨰 일반 매개 변수(Long)로 사용
    // Long을 쓰는 이유는 PK라서일까??
    List<Board> findAllByOrderByModifiedAtDesc(); //보드에 있는 게시글을 ModifiedAt 기준으로 descending order (최신순)
    Optional<Board> findByIdAndPassword(Long id, String password); // 지정된 ID와 암호를 가진 Board 엔티티를 포함할 수 있는 선택적 개체를 반환
    Optional<Board> deleteByIdAndPassword(Long id, String password); // 지정된 ID와 암호를 가진 Board 엔티티가 있으면 삭제
}

// List <> and Optional <> are both data structures used to store objects.
// List <> 는 순서가 지정된 개체 모음을 나타내고 중복 항목 포함 가능 / 여러 개체를 특정 순서로 작업하고 수행해야 할 때 유용
// Optional <> 은 값이 존재할 수도 있고, 존재하지 않을 수도 있음을 나타내는데 사용 / 단일 개체를 포함하거나 비어 있을 수 있음 / 작업을 수행하기 전에 값이 존재하는지 여부를 확인 해야할 때 유용

// 요약: List <> - 개체 모음을 나타내는데 사용 / Optional <> - 존재하거나 존재하지 않을 수 있는 단일 개체를 나타내는 데 사용

// 그렇다면 위에서 List를 사용하는 이유는 이미 "게시글"이 이미 존재하고 있고, 거기 안에서 수행해야해서 사용
// Optional을 사용한 이유는 id와 password가 매칭하는 "게시글"이 있어야 찾거나 삭제할 수 있기 때문?