package amal.example.Exam_Generation;

class QuestionNotFoundException extends RuntimeException{

    QuestionNotFoundException(Long id){
        super("Could not find question "+id);
    }
}
