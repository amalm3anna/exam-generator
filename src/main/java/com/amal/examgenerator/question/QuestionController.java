package com.amal.examgenerator.question;

import amal.example.examgenerator.Question;
import amal.example.examgenerator.QuestionModelAssembler;
import amal.example.examgenerator.QuestionNotFoundException;
import amal.example.examgenerator.QuestionRepository;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class QuestionController {

    @Autowired
    private final QuestionRepository questionRepository;
    private final QuestionModelAssembler assembler;

    QuestionController(QuestionRepository questionRepository, QuestionModelAssembler assembler){
        this.questionRepository=questionRepository;
        this.assembler=assembler;
    }

    @GetMapping("/questions")
    CollectionModel<EntityModel<Question>> getAllQuestions(){
        List<EntityModel<Question>> entityModel= questionRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(entityModel, linkTo(methodOn(QuestionController.class).getAllQuestions()).withSelfRel());
    }

    @GetMapping("/questions/{id}")
    EntityModel<Question> getQuestion(@PathVariable Long id){
        Question question=questionRepository.findById(id).orElseThrow(()-> new QuestionNotFoundException(id));
        return assembler.toModel(question);
    }

    @PostMapping("/questions")
    ResponseEntity<?> addQuestion(@RequestBody Question newQuestion){
        EntityModel<Question> q_EntityModel= assembler.toModel(questionRepository.save(newQuestion));
        return ResponseEntity.created(q_EntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(q_EntityModel);
    }

    @PutMapping("/questions/{id}")
    ResponseEntity<?> updateQuestion(@RequestBody Question newQuestion, @PathVariable Long id){
        Question updatedQuestion= questionRepository.findById(id).map(question->
        {question.setQuestionPart(newQuestion.getQuestionPart());
            return questionRepository.save(question);
        }).orElseGet(()-> {newQuestion.setId(id);
            return questionRepository.save(newQuestion);
        });
        EntityModel<Question> q_EntityModel=assembler.toModel(updatedQuestion);
        return ResponseEntity.created(q_EntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(q_EntityModel);
    }

    @DeleteMapping("/questions/{id}")
    ResponseEntity<?> deleteQuestion(@PathVariable Long id){
        questionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}