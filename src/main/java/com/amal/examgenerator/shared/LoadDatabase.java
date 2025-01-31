package com.amal.examgenerator.shared;

import amal.example.examgenerator.exam.Exam;
import amal.example.examgenerator.exam.ExamRepository;
import amal.example.examgenerator.question.Question;
import amal.example.examgenerator.question.QuestionRepository;
import amal.example.examgenerator.subject.Subject;
import amal.example.examgenerator.subject.SubjectRepository;
import com.amal.examgenerator.question.Question;
import com.amal.examgenerator.subject.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log=LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ExamRepository examRepository, SubjectRepository subjectRepository, QuestionRepository questionRepository){
        return args -> {
            subjectRepository.save(new Subject("MATH101"));
            subjectRepository.save(new Subject("ENGL151"));
            subjectRepository.save(new Subject("MATH301"));
            subjectRepository.save(new Subject("EENG325"));
            subjectRepository.findAll().forEach(subject -> log.info("Requested subject: "+subject));

            questionRepository.save(new Question('a',"MATH101", false));
            questionRepository.save(new Question('b', "ENGL151", false));
            questionRepository.save(new Question('c', "MATG301", false));
            questionRepository.save(new Question('d', "MATH101", false));
            questionRepository.save(new Question('e', "EENG325", true));
            questionRepository.save(new Question('f', "EENG325", true));
            questionRepository.save(new Question('g', "MATH101", false));
            questionRepository.save(new Question('h', "ENGL151", false));
            questionRepository.save(new Question('i', "ENGL151", false));
            questionRepository.findAll().forEach(question -> log.info("question"+ question));

            examRepository.save(new Exam("MATH101", 2));
            examRepository.save(new Exam("ENGL151", 3));
            examRepository.findAll().forEach(exam -> log.info("exam: "+exam));
        };
    }
}
