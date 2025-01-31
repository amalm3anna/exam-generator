package com.amal.examgenerator.subject;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subject")
@Getter
@Setter
class SubjectEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToMany(mappedBy = "subject")
  private Set<GeneratedExam> generatedExams;
  private String subject;
  private boolean deleted = Boolean.FALSE;
}

