package com.amal.examgenerator.exam.generated;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
class GeneratedExamEntity {
  
  @EmbeddedId
  private GeneratedExamKey id;
  
  @ManyToOne
  @MapsId("questionId")
  @JoinColumn(name = "question_id")
  private Question question;
  
  @ManyToOne
  @MapsId("examId")
  @JoinColumn(name = "exam_id")
  private Exam exam;
  
  @ManyToOne
  @MapsId("SubjectId")
  @JoinColumn(name = "subject_id")
  private Subject subject;
  
}
