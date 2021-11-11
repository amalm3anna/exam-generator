package amal.example.Exam_Generation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ExamModelAssembler implements RepresentationModelAssembler<Exam, EntityModel<Exam>> {

    @Override
    public EntityModel<Exam> toModel(Exam exam){
        return EntityModel.of(exam, linkTo(methodOn(ExamController.class).getExam(exam.getId())).withSelfRel());
    }
}

