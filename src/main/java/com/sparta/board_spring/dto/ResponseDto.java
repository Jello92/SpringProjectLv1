package com.sparta.board_spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // automatically generate getter methods for all private fields in the class
@Setter // automatically generate setter method for all private fields in the class
public class ResponseDto { //ResponseDto의 정의
    private boolean success; // private field that stores a boolean value indicating whether an operation was successful or not
    private String message; // private field that stores a message to be displayed to the user indicating the result of an operation

    public ResponseDto(boolean success, String message) { // a constructor method that takes two parameters: a boolean success value and a message string
        this.success = success;
        this.message = message;
    }
}
// ResponseDto Class is a simple dto that is used to encapsulate the resul of an operation and send it back to the caller.