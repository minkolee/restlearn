package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.api.StudentResource;
import cc.conyli.restlearn.api.StudentResourceAssembler;
import cc.conyli.restlearn.entity.Student;
import cc.conyli.restlearn.repository.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/myapi/students", produces = "application/json")
@CrossOrigin("*")
public class StudentRestController {

    private StudentRepo studentRepo;

    @Autowired
    public StudentRestController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    //HATEOAS方法
//    @GetMapping
//    public Resources<StudentResource> showStudentList() {
//        List<Student> students = (List<Student>)studentRepo.findAll();
//
//        List<StudentResource> studentResources = new StudentResourceAssembler().toResources(students);
//        Resources<StudentResource> studentsHATEOAS = new Resources<>(studentResources);
//
//        studentsHATEOAS.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(StudentRestController.class).showStudentList()).withRel("students"));
//
//        return studentsHATEOAS;
//    }

    @GetMapping
    public List<Student> showStudentList() {
        return studentRepo.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student) {
        log.info(student.toString());
        return studentRepo.save(student);
    }


    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Student> replaceStudent(@PathVariable("id") int id, @RequestBody Student student) {
        Optional<Student> targetStudent = studentRepo.findById(id);
        if (targetStudent.isPresent()) {
            Student theStudent = targetStudent.get();
            theStudent.setFirstName(student.getFirstName());
            theStudent.setLastName(student.getLastName());
            theStudent.setCourseId(student.getCourseId());
            return new ResponseEntity<>(studentRepo.save(theStudent), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Student> patchStudent(@PathVariable("id") int id, @RequestBody Student student) {
        Optional<Student> targetStudent = studentRepo.findById(id);
        if (targetStudent.isPresent()) {
            Student theStudent = targetStudent.get();
            if (student.getFirstName() != null) {
                theStudent.setFirstName(student.getFirstName());
            }
            if (student.getLastName() != null) {
                theStudent.setLastName(student.getLastName());
            }
            if (student.getCourseId() != null) {
                theStudent.setCourseId(student.getCourseId());
            }
            return new ResponseEntity<>(studentRepo.save(theStudent), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable("id") int id) {
        Optional<Student> targetStudent = studentRepo.findById(id);
        if (targetStudent.isPresent()) {
            studentRepo.delete(targetStudent.get());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
