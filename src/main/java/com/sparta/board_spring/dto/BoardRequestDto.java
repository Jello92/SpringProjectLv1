package com.sparta.board_spring.dto;

import lombok.Getter;

@Getter // 해당 private 필드에 있는 인스턴스 변수를 다른 Class에서 사용할 수 있도록 함
public class BoardRequestDto { //BoardRequestDto에 대한 정의 (Blueprint) - 사용자가 input한 데이터를 넣어두는 컨테이너
    private String title; // input 제목
    private String content; // input 내용
    private String author; // input 작성자
    private String password; //input 비번
}


// 가장 먼저 DTO를 명확하게 알고가자! DTO = Data Transfer Object 즉 데이터를 옮긴다
// BoardRequestDto - user input을 transfer 하기 위해 존재
// Presentation Layer -> (user input) -> BoardRequestDto -> (transferring) -> create or update Board Entity