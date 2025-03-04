package com.amal.examgenerator.question;

import amal.example.examgenerator.QuestionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class QuestionNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String questionNotFoundHandler(QuestionNotFoundException ex){
        return ex.getMessage();
    }
}
