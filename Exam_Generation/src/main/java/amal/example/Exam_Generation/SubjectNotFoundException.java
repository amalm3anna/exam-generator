package amal.example.Exam_Generation;

class SubjectNotFoundException extends RuntimeException {

    SubjectNotFoundException(Long id){
        super("Could not find subject "+id);
    }
}
