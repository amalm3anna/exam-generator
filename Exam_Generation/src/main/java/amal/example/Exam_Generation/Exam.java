package amal.example.Exam_Generation;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "table_exam")
class Exam {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") Long id;
    private @OneToMany(mappedBy = "exam") Set<GeneratedExam> generatedExams;
    private String subject;
    private int numofQuestions;

    protected Exam(){}

    public Exam(String subject, int numofQuestions){
        this.numofQuestions=numofQuestions;
        this.subject=subject;
    }

    public Long getId(){
        return this.id;
    }

    public Set<GeneratedExam> getGeneratedExams(){
        return this.generatedExams;
    }

    public String getSubject(){
        return this.subject;
    }

    public int getNumOfQuestions(){
        return this.numofQuestions;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setGeneratedExams(Set<GeneratedExam> generatedExams){
        this.generatedExams=generatedExams;
    }

    public void setSubject(String subject){
        this.subject=subject;
    }

    public void setNumOfQuestions(int numofQuestions){
        this.numofQuestions=numofQuestions;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (!(o instanceof Exam))
            return false;
        Exam exam= (Exam) o;
        return Objects.equals(this.id, exam.id) && Objects.equals(this.generatedExams, exam.generatedExams)&&Objects.equals(this.numofQuestions, exam.numofQuestions)
                && Objects.equals(this.subject, exam.subject);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.generatedExams, this.numofQuestions, this.subject);
    }

    @Override
    public String toString(){
        return String.format("Exam[ id= %d, number of questions= %d, subject= '%s']", id, numofQuestions, subject);
    }
}