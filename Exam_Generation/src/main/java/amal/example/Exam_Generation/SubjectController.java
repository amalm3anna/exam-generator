package amal.example.Exam_Generation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

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
class SubjectController {

    @Autowired
    private final SubjectRepository subjectRepository;
    private final SubjectModelAssembler assembler;

    SubjectController(SubjectRepository subjectRepository, SubjectModelAssembler assembler){
        this.subjectRepository=subjectRepository;
        this.assembler=assembler;
    }

    @GetMapping("/subjects")
    CollectionModel<EntityModel<Subject>> getAllSubjects(){
        List<EntityModel<Subject>> s_EntityModel= subjectRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(s_EntityModel, linkTo(methodOn(SubjectController.class).getAllSubjects()).withSelfRel());
    }

    @GetMapping("/subjects/{id}")
    EntityModel<Subject> getSubject(@PathVariable Long id){
        Subject subject= subjectRepository.findById(id).orElseThrow(()-> new SubjectNotFoundException(id));
        return assembler.toModel(subject);
    }

    @PostMapping("/subjects")
    ResponseEntity<?> addSubject(@RequestBody Subject newSubject){
        Subject subject=subjectRepository.save(newSubject);
        EntityModel<Subject> s_EntityModel= assembler.toModel(subject);
        return ResponseEntity.created(s_EntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(s_EntityModel);
    }

    @PutMapping("/subjects/{id}")
    ResponseEntity<?> updateSubject(@RequestBody Subject newSubject, @PathVariable Long id){
        Subject updatedSubject= subjectRepository.findById(id).map(subject ->
        {subject.setSubject(newSubject.getSubject());
            return subjectRepository.save(subject);
        }).orElseGet(() -> {newSubject.setId(id);
            return subjectRepository.save(newSubject);
        });
        EntityModel<Subject> s_EntityModel= assembler.toModel(updatedSubject);
        return ResponseEntity.created(s_EntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(s_EntityModel);
    }

    @DeleteMapping("/subjects/{id}")
    ResponseEntity<?> deleteSubject(@PathVariable Long id){
        subjectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
