package amal.example.Exam_Generation;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "table_question")
@SQLDelete(sql = "UPDATE table_question SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
class Question {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    private @OneToMany(mappedBy = "question") Set<GeneratedExam> generatedExams;
    private char question_part;
    private String subject;
    private boolean deleted= Boolean.FALSE;

    protected Question(){}

    public Question(char question_part, String subject, boolean deleted){
        this.question_part=question_part;
        this.subject=subject;
        this.deleted=deleted;
    }

    public Long getId(){
        return this.id;
    }

    public Set<GeneratedExam> getGeneratedExams(){
        return this.generatedExams;
    }

    public char getQuestionPart(){
        return this.question_part;
    }

    public String getSubject(){
        return this.subject;
    }

    public boolean getDeleted(){
        return this.deleted;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setGeneratedExams(Set<GeneratedExam> generatedExams){
        this.generatedExams=generatedExams;
    }

    public void setQuestionPart(char question_part){
        this.question_part=question_part;
    }

    public void setSubject(String subject){
        this.subject=subject;
    }

    public void setDeleted(boolean deleted){
        this.deleted=deleted;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (!(o instanceof Question))
            return false;
        Question question= (Question)o;
        return Objects.equals(this.id, question.id) && Objects.equals(this.generatedExams, question.generatedExams)
                && Objects.equals(this.question_part, question.question_part) && Objects.equals(this.subject, question.subject) && Objects.equals(this.deleted, question.deleted);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.generatedExams, this.question_part, this.subject, this.deleted);
    }

    @Override
    public String toString(){
        return String.format("Question[ id= %d, questionpart= '%s', subject= '%s']", id, question_part, subject);
    }
}

