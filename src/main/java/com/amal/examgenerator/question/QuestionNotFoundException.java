package com.amal.examgenerator.question;

class QuestionNotFoundException extends RuntimeException{

    QuestionNotFoundException(Long id){
        super("Could not find question "+id);
    }
}
