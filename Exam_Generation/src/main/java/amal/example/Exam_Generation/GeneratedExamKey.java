package amal.example.Exam_Generation;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class GeneratedExamKey implements Serializable {

    @Column(name = "question_id")
    Long questionId;

    @Column(name = "exam_id")
    Long examId;

    @Column(name = "subject_id")
    Long subjectId;

    public GeneratedExamKey(){}

    public GeneratedExamKey(Long questionId, Long examId, Long subjectId){
        this.questionId=questionId;
        this.examId=examId;
        this.subjectId=subjectId;
    }

    public Long getQuestionId(){
        return this.questionId;
    }

    public Long getExamId(){
        return this.examId;
    }

    public Long getSubjectId(){
        return this.subjectId;
    }

    public void setQuestionId(Long questionId){
        this.questionId=questionId;
    }

    public void setExamId(Long examId){
        this.examId=examId;
    }

    public void setSubjectId(Long subjectId){
        this.subjectId=subjectId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if(!(o instanceof GeneratedExamKey))
            return false;
        GeneratedExamKey key=(GeneratedExamKey)o;
        return Objects.equals(this.questionId, key.questionId) && Objects.equals(this.examId, key.examId) && Objects.equals(this.subjectId, key.subjectId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.questionId, this.examId, this.subjectId);
    }

    @Override
    public String toString(){
        return String.format("GeneratedExamKey[exam_id= %d, question_id= %d, subject_id=%d]", examId, questionId, subjectId);
    }
}

