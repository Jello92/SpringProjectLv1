package com.sparta.board_spring.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
}
