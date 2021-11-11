package amal.example.Exam_Generation;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
class GeneratedExam {

    @EmbeddedId
    GeneratedExamKey id;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    Question question;

    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    Exam exam;

    @ManyToOne
    @MapsId("SubjectId")
    @JoinColumn(name = "subject_id")
    Subject subject;

    public GeneratedExam(){}

    public GeneratedExam(Exam exam, Subject subject, Question question){
        this.exam=exam;
        this.subject=subject;
        this.question=question;
    }

    public GeneratedExamKey getKeyId(){
        return this.id;
    }

    public Exam getExam(){
        return this.exam;
    }

    public Subject getSubject(){
        return this.subject;
    }

    public Question getQuestion(){
        return this.question;
    }

    public void setKeyId(GeneratedExamKey id){
        this.id=id;
    }

    public void setExam(Exam exam){
        this.exam=exam;
    }

    public void setSubject(Subject subject){
        this.subject=subject;
    }

    public void setQuestion(Question question){
        this.question=question;
    }

    @Override
    public boolean equals(Object o){
        if (this==o)
            return true;
        if(!(o instanceof GeneratedExam))
            return false;
        GeneratedExam g_exam=(GeneratedExam)o;
        return Objects.equals(this.exam, g_exam.exam) && Objects.equals(this.subject, g_exam.subject) && Objects.equals(this.question, g_exam.question);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.exam, this.subject, this.question);
    }

}
