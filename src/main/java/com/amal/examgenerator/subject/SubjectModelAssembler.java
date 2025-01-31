package com.amal.examgenerator.subject;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class SubjectModelAssembler implements RepresentationModelAssembler<SubjectEntity, EntityModel<SubjectEntity>> {

    @Override
    public EntityModel<SubjectEntity> toModel(SubjectEntity subjectEntity){
        return EntityModel.of(subjectEntity, linkTo(methodOn(SubjectController.class).getSubject(subjectEntity.getId())).withSelfRel(),
                linkTo(methodOn(SubjectController.class).getAllSubjects()).withRel("subjects"));
    }
}
