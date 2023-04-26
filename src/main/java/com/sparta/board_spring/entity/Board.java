package com.sparta.board_spring.entity;

import com.sparta.board_spring.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //정보를 가져와야함으로 Getter을 사용
@Entity // DB 테이블 역할임을 알리는 표시
@NoArgsConstructor //기본생성자 만들어 줌
public class Board extends Timestamped{ //Timestamped를 상속받아 createdAt, modifiedAt 적용 될 수 있도록 함
    @Id //PK를 나타내기 위해 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)// GeneratedValue는 PK값에 대한 생성 전략을 제공. GenerationTyep.IDENTITY - auto increment
    private Long id; //즉, private Long id를 Primary Key로 설정

    @Column(nullable = false) //행 생성 - nullable not allowed
    private String title; // 게시물 제목

    @Column(nullable = false) //행 생성 - nullable not allowed
    private String content; // 게시물 내용

    @Column(nullable = false) //행 생성 - nullable not allowed
    private String author; // 게시물 작성자

    @Column(nullable = false) //행 생성 - nullable not allowed
    private String password; // 비밀번호

    public Board(BoardRequestDto boardRequestDto) { //BoardRequestDto를 parameter로 사용하고 boardRequestDto 객체 값을 사용하여 Board 객체의 필드를 초기화 하는 생성자
        // boardRequestDto개체의 해당 값 기준으로 Board 개체의 제목, 내용, 작성자 및 비번 필드를 설정
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();
    }

    public void update(BoardRequestDto boardRequestDto) { //BoardRequestDto를 parameter로 사용하교 boardRequestDto 객체의 새 값으로 Board 객체의 필드를 업데이트
        //Board 개체의 제목, 내용, 작성자 및 비번 필드를 requestDto에서 제공된 새 값으로 설정
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();


        // 위 두 메서드는 BoardRequestDto에서 객체로 데이터를 사용하여 Board Entity를 만들고 업데이트 하는데 사용이 됨
        // BoardRequestDto는 일반적으로 사용자가 양식에 입력한 데이터를 보관
        // Board Entity는 DB 테이블의 레코드를 나타냄
        // 결과적으로 BoardRequestDto의 필드를 Board Entity 필드에 매핑하여 사용자 입력을 기반으로 DB에 Board 레코드를 만들고 업데이트
    }
}
