package com.amal.examgenerator.exam;

class ExamNotFoundException extends RuntimeException{

    ExamNotFoundException (Long id){
        super("Could not find exam "+id);
    }
}
