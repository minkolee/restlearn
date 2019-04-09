package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.entity.Course;
import cc.conyli.restlearn.entity.Student;
import cc.conyli.restlearn.repository.CourseRepo;
import cc.conyli.restlearn.repository.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/api", produces = "application/json")
@CrossOrigin("*")
public class RestController {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;

    public RestController(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @GetMapping("/students")
    public Iterable<Student> showStudentList() {
        return studentRepo.findAll();
    }

    @GetMapping("/courses")
    public Iterable<Course> showCourseList() {
        return courseRepo.findAll();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Optional<Course> course = courseRepo.findById(id);
        if (course.isPresent()) {
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/students", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student) {
        log.info(student.toString());
        return studentRepo.save(student);
    }

    @PostMapping(path = "/courses", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Course addStudent(@RequestBody Course course) {
        log.info(course.toString());
        return courseRepo.save(course);
    }

    //传入整个对象，如果有字段错误，就会导致not null字段保存错误
    @PutMapping(path = "/student/{id}", consumes = "application/json")
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

    //仅根据部分值就可以更新，哪怕传错也没事
    @PatchMapping(path = "/student/{id}", consumes = "application/json")
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

    @DeleteMapping(path = "/student/{id}", consumes = "application/json")
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
