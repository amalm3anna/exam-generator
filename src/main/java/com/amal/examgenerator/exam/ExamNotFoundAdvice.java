package com.amal.examgenerator.exam;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ExamNotFoundAdvice {


    @ResponseBody
    @ExceptionHandler(ExamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ExamNotFoundHandler(ExamNotFoundException ex){
        return ex.getMessage();
    }
}
