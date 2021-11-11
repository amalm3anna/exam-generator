package amal.example.Exam_Generation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class SubjectModelAssembler implements RepresentationModelAssembler<Subject, EntityModel<Subject>> {

    @Override
    public EntityModel<Subject> toModel(Subject subject){
        return EntityModel.of(subject, linkTo(methodOn(SubjectController.class).getSubject(subject.getId())).withSelfRel(),
                linkTo(methodOn(SubjectController.class).getAllSubjects()).withRel("subjects"));
    }
}
