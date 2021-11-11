package amal.example.Exam_Generation;

class ExamNotFoundException extends RuntimeException{

    ExamNotFoundException (Long id){
        super("Could not find exam "+id);
    }
}
