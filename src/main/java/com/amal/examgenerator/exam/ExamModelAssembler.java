package com.amal.examgenerator.exam;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ExamModelAssembler implements RepresentationModelAssembler<ExamEntity, EntityModel<ExamEntity>> {

    @Override
    public EntityModel<ExamEntity> toModel(ExamEntity examEntity){
        return EntityModel.of(examEntity, linkTo(methodOn(ExamController.class).getExam(examEntity.getId())).withSelfRel());
    }
}

