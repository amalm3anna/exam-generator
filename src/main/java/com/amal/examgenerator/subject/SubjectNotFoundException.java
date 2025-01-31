package com.amal.examgenerator.subject;

class SubjectNotFoundException extends RuntimeException {

    SubjectNotFoundException(Long id){
        super("Could not find subject "+id);
    }
}
