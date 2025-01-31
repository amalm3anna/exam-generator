package com.amal.examgenerator;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class ExamGenerationApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(ExamGenerationApplication.class, args);
    log.info("Exam Generator Application Started");
  }
}
