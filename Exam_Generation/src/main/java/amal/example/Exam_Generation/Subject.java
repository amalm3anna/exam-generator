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
@Table(name = "table_subject")
@SQLDelete(sql = "UPDATE table_subject SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
class Subject {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    private @OneToMany(mappedBy = "subject") Set<GeneratedExam> generatedExams;
    private String subject;
    private boolean deleted= Boolean.FALSE;

    protected Subject(){}

    public Subject(String subject){
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

    public boolean getDeleted(){
        return this.deleted;
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

    public void setDeleted(boolean deleted){
        this.deleted=deleted;
    }

    @Override
    public boolean equals(Object o){
        if(this ==o)
            return true;
        if(!(o instanceof Subject))
            return false;
        Subject subject=(Subject) o;
        return Objects.equals(this.id, subject.id) && Objects.equals(this.generatedExams, subject.generatedExams) && Objects.equals(this.subject, subject.subject);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.generatedExams, this.subject);
    }

    @Override
    public String toString(){
        return String.format("Subject[ id= %d, subject= '%s']", id, subject);
    }

}

