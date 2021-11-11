package amal.example.Exam_Generation;

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
    EntityModel<Exam> getExam(@PathVariable Long id){
        Exam exam=examRepository.findById(id).orElseThrow(()-> new ExamNotFoundException(id));
        return assembler.toModel(exam);
    }

    @PostMapping("/exams")
    ResponseEntity<?> addExam(@RequestBody Exam newExam){
        EntityModel<Exam> q_EntityModel= assembler.toModel(examRepository.save(newExam));
        return ResponseEntity.created(q_EntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(q_EntityModel);
    }

}

