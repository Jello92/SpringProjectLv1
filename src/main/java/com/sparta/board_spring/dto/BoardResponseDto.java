package com.sparta.board_spring.dto;

import com.sparta.board_spring.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter  // 해당 private 필드에 있는 인스턴스 변수를 다른 Class에서 사용할 수 있도록 함
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
public class BoardResponseDto { // - 사용자에게 제공될(output) 데이터를 넣어두는 컨테이너
    private Long id; // output id
    private String title; // output title
    private String content; // output content
    private LocalDateTime createdAt; // output createdAt
    private LocalDateTime modifiedAt; // output modifiedAt
}

//위에 BoardResponseDto는 overloading의 예시
