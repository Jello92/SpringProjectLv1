package com.sparta.board_spring.controller;

import com.sparta.board_spring.dto.BoardRequestDto;
import com.sparta.board_spring.dto.ResponseDto;
import com.sparta.board_spring.entity.Board;
import com.sparta.board_spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController // RESTful Controller임을 나타내는데 사용
@RequiredArgsConstructor // used to generate a constructor that injects the boardService dependency
// In another word, this annotation automatically GENERATES a constructor with arguments for all final fields in the class.
// 만약 해당 annotation이 없었다면 아래 (1번 참고)와 같이 직접 작성해야함
public class BoardController {

    private final BoardService boardService; // instance 변수 선언: boardService를 인스턴스 변수로 만들어 BoardController 클래스 내의 모든 메서드에서 액세스 할 수 있음. 상호작용 가능.

//1번 // public BoardController(BoardService boardService) {
     // this.boardService = boardService;
     // }  <- @RequiredArgsConstructor로 인하여서 생략 가능

    @GetMapping("/")
    public ModelAndView home (){ //home - handles GET requests to the root URL, it returns "ModelAndView" object
        return new ModelAndView("index.html");
    }


    @PostMapping("/api/boards") // "/api/boards"에 Post Request 처리
    public Board createBoard (@RequestBody BoardRequestDto boardRequestDto){ //BoardRequestDto에서 개체를 가져옴
        return boardService.createBoard(boardRequestDto); //boardService의 createBoard 메소드를 사용하여 새로운 Board object 생성
    }

    @GetMapping("/api/boards") // "/api/boards"에 Get Request 처리
    public List<Board> getBoard(){ // 목록검색 (List 사용 이유 BoardRepository 참고)
        return boardService.getBoard(); // boardService를 사용하여 모든 게시글 목록을 검색하고 반환
    }

    @PutMapping("/api/boards/{id}") // "/api/boards/{id}"에 Put Request 처리
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){ //요청 본문에서 BoardRequestDto 개체를 가져 옴
        return boardService.update(id, boardRequestDto); //boardService를 사용하여 지정된 ID로 Board의 게시글 업데이트
    }
    //

    @DeleteMapping("/api/boards/{id}")
    public ResponseDto deleteBoard (@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) { //요청 본문에서 BoardRequestDto 개체를 가져 옴
        return boardService.deleteBoard(id, boardRequestDto.getPassword()); //boardService를 사용하여 지정된 ID와 password로 Board의 게시글 삭제
    }
}

// URI (Uniform Resource Identifier) - 통합 자원 식별자 / Resource란 URI로 식별이 가능한 모든 종류의 자원 (웹 브라우저 파일 및 그 이외의 리소스 포함)을 지칭
// URI는 즉, 자원 자체를 식별하는 고유한 문자열 sequence
// @RequestParam과 @PathVariable은 http의 비연결성을 극복하고 데이터를 전달하기 위한 annotation 중 하나로, uri를 통해 전달된 값을 파라미터로 받아오는 역할
// http://localhost:8000/board?page=1&listSize=10 (@RequestParam) / http://localhost:8000/board/1 (@PathVariable)
// @PathVariable - {변수}와 동일한 이름을 갖는 파라미터 (위에 PutMapping이나 DeleteMapping의 경우 "/api/baords/{id}"에서 {id}와 매칭시키기 위함


// 비동기통신을 하기위해서는 client에서 서버로 요청 msg 보낼 때, 본문에 데이터를 담아서 보내고 / 서버에서 client로 응답 보낼 때도 vice-versa. 본문 = Body
//@RequestBody - 요청본문 (해당 annotation이 붙은 파라미터에는 http요청의 본문이 그대로 전달된다) - 즉, input 값이 그대로 전달 된다는 건가..?
//@RequestBody - 응답본문 (해당 annotation이 붙은 파라미터에는 자바객체를 http요청의 바디내용으로 매핑하여 클라이언트로 전송한다