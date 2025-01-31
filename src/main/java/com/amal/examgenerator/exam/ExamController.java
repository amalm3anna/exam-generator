package com.amal.examgenerator.exam;

import amal.example.examgenerator.Exam;
import amal.example.examgenerator.ExamModelAssembler;
import amal.example.examgenerator.ExamNotFoundException;
import amal.example.examgenerator.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExamController {

    @Autowired
    private final ExamRepository examRepository;
    private final ExamModelAssembler assembler;

    ExamController(ExamRepository examRepository, ExamModelAssembler assembler){
        this.examRepository=examRepository;
        this.assembler=assembler;
    }

    @GetMapping("/exams/{id}")
    EntityModel<ExamEntity> getExam(@PathVariable Long id){
        ExamEntity examEntity =examRepository.findById(id).orElseThrow(()-> new ExamNotFoundException(id));
        return assembler.toModel(examEntity);
    }

    @PostMapping("/exams")
    ResponseEntity<?> addExam(@RequestBody ExamEntity newExamEntity){
        EntityModel<ExamEntity> q_EntityModel= assembler.toModel(examRepository.save(newExamEntity));
        return ResponseEntity.created(q_EntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(q_EntityModel);
    }

}

