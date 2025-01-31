package com.amal.examgenerator.exam;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exam")
@Getter
@Setter
class ExamEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  @OneToMany(mappedBy = "exam")
  private Set<> generatedExams;
  private String subject;
  private int numofQuestions;
}