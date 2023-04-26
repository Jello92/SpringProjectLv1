package com.sparta.board_spring.service;

import com.sparta.board_spring.dto.BoardRequestDto;
import com.sparta.board_spring.dto.ResponseDto;
import com.sparta.board_spring.entity.Board;
import com.sparta.board_spring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // RESTful Service임을 나타내는데 사용
@RequiredArgsConstructor //BoardRepository 생성자 자동 생성
public class BoardService {

    private final BoardRepository boardRepository; // BoardRepository를 인스턴스 변수로 만들어 BoardService 클래스 내의 모든 메서드에 엑세스 가능

    @Transactional // 맨 아래 참고
    public Board createBoard(BoardRequestDto boardRequestDto){ //게시글을 생성하는 메소드 - BoardRequestDto를 매개변수로 사용
        Board board = new Board(boardRequestDto); //BoardReqeustDto를 매개변수로 사용하는 Board 클래스의 생성자를 사용하여 BoardRequestDto에서 새 Board 객체 생성
        //BoardRequestDto를 매개변수로 사용하고 Board의 필드를 BoardRequestDto 개체의 해당 필드 값으로 설정하는 생성자가 있음
        boardRepository.save(board); // boardRepository.save() 메소드를 사용하여 새 Board(board)를 저장 / .save()메서드는 Board를 매개변수로 사용하여 데이터베이스에 유지
        return board; // board값을 return
    }
    @Transactional(readOnly = true) // readOnly 속성을 통해 읽기 전용으로 설정 가능 / transaction이 commit되어도 영속성 컨텍스트를 플러시하지 않음. 플러시할 때 수행되는 엔티티의 스냅샷 비교 로직이 수행되지 않으므로 성능 향상
    public List<Board> getBoard() { // List of Board에서 getBoard를 하여 return하는 메소드
        return boardRepository.findAllByOrderByModifiedAtDesc(); //boardRepository에 있는 모든 게시글들은 modifiedAt기준 descending order로 반환
    }
    // 즉, 해당 메소드는 retrieves a list of 'Board' from the DB using BoardRepository and returns them. readOnly이기 떄문에 수정되는건 없음

    @Transactional
    public Board update(Long id, BoardRequestDto boardRequestDto) { // 게시글 업데이트할 게시글의 id와 Board에 대한 업데이트된 정보를 가지고 있는 BoardRequestDto, 두개의 인수 사용
        Board board = boardRepository.findById(id).orElseThrow( //BoardRepository의 findById() 메서드를 사용하여 데이터베이스에서 지정된 id를 가진 게시글을 검색. 게시글이 데이터베이스에 존재하지 않는 경우 orElseThrow()메서드 사용
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.") //"아이디가 존재하지 않습니다"라는 메세지를 보냄
        );
        Board board1 = boardRepository.findByIdAndPassword(id, boardRequestDto.getPassword()).orElseThrow( //BoardRepository의 findByIdAndPassword() 메서드를 사용하여 데이터베이스에서 지정된 id와 암호로 게시글을 검색. 게시글이 데이터베이스에 존재하지 않거나 암호가 일치하지 않으면 경우 orElseThrow()메서드 호출
                ()-> new IllegalArgumentException("비밀번호가 일치하지 않습니다.") //"비밀번호가 일치하지 않습니다"라는 메세지를 보냄
        );
        board.update(boardRequestDto); //Board 클래스의 update()메소드를 사용하여 BoardRequestDto 개체의 정보로 Board 개체를 업데이트.
        return board; //업데이트된 Board개체를 반환
    }

    @Transactional
    public ResponseDto deleteBoard(Long id, String password) { // 삭제할 게시글의 id와 비밀번호를 가져와 Board에 삭제하려고하는 해당 게시글이 있는지 확인
        Board board = boardRepository.findById(id).orElseThrow( //BoardRepository의 findById() 메서드를 사용하여 데이터베이스에서 지정된 id를 가진 게시글을 검색. 게시글이 데이터베이스에 존재하지 않는 경우 orElseThrow()메서드 사용
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.") //"아이디가 존재하지 않습니다"라는 메세지를 보냄
        );

        Board board1 = boardRepository.findByIdAndPassword(id, password).orElseThrow( //BoardRepository의 findByIdAndPassword() 메서드를 사용하여 데이터베이스에서 지정된 id와 암호로 게시글을 검색. 게시글이 데이터베이스에 존재하지 않거나 암호가 일치하지 않으면 경우 orElseThrow()메서드 호출
                ()-> new IllegalArgumentException("비밀번호가 일치하지 않습니다.") //"비밀번호가 일치하지 않습니다"라는 메세지를 보냄
        );
        boardRepository.deleteByIdAndPassword(id, password); //BoardRepository에 있는 게시글을 id와 password를 매칭시켜 삭제
        return new ResponseDto(true, "Deleted Successfully"); //ResponseDto로 return. ResponseDto에 있는 메소드를 사용?
    }
}

// What is Transaction? DB 관리 시스템 또는 유사한 시스템에서 상호작용의 단위. / 사용 목적: 오류로부터 복구를 허용하고 데이터베이스를 일관성있게 유지하는 안정적인 작업 단위를 제공
// Transaction process : 트랜잭션 시작 -> 비지니스 로직 실행 -> 트랜잭션 커밋 (만약 쿼리 하나가 실패하면, 데이터베이스 시스템은 전체 트랜잭션 또는 실패한 쿼리를 롤백)
// @Transactional - 클래스, 메소드에 선언되면 해당 클래스에 트랜잭션이 적용된 프록시 객체 생성 -> 프록시 객체는 @Transacational 포함된 메서드가 호출될 경우, 트랜잭션을 시작하고 Commit or Rollback 수행
