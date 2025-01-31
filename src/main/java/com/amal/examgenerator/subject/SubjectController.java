package com.amal.examgenerator.subject;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "subject")
@Validated
@AllArgsConstructor
@Log4j2
class SubjectController {
  
  private final SubjectRepository subjectRepository;
  private final SubjectModelAssembler assembler;
  
  @GetMapping("")
  CollectionModel<EntityModel<SubjectEntity>> getAllSubjects() {
    List<EntityModel<SubjectEntity>> s_EntityModel = subjectRepository
            .findAll()
            .stream()
            .map((SubjectEntity subjectEntity) -> assembler.toModel(subjectEntity))
            .collect(Collectors.toList());
    return CollectionModel.of(
            s_EntityModel,
            linkTo(methodOn(SubjectController.class).getAllSubjects()).withSelfRel());
  }
  
  @GetMapping("{id}")
  EntityModel<SubjectEntity> getSubject(@PathVariable Long id) {
    SubjectEntity subjectEntity = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException(id));
    return assembler.toModel(subjectEntity);
  }
  
  @PostMapping("")
  ResponseEntity<?> addSubject(@RequestBody SubjectEntity newSubjectEntity) {
    SubjectEntity subjectEntity = subjectRepository.save(newSubjectEntity);
    EntityModel<SubjectEntity> s_EntityModel = assembler.toModel(subjectEntity);
    return ResponseEntity.created(s_EntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(s_EntityModel);
  }
  
  @PutMapping("{id}")
  ResponseEntity<?> updateSubject(@RequestBody SubjectEntity newSubjectEntity, @PathVariable Long id) {
    SubjectEntity updatedSubjectEntity = subjectRepository.findById(id).map(subjectEntity ->
    {
      subjectEntity.setSubject(newSubjectEntity.getSubject());
      return subjectRepository.save(subjectEntity);
    }).orElseGet(() -> {
      newSubjectEntity.setId(id);
      return subjectRepository.save(newSubjectEntity);
    });
    EntityModel<SubjectEntity> s_EntityModel = assembler.toModel(updatedSubjectEntity);
    return ResponseEntity.created(s_EntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(s_EntityModel);
  }
  
  @DeleteMapping("{id}")
  ResponseEntity<?> deleteSubject(@PathVariable Long id) {
    subjectRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
