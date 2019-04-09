package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.entity.Course;
import cc.conyli.restlearn.entity.Student;
import cc.conyli.restlearn.repository.CourseRepo;
import cc.conyli.restlearn.repository.StudentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;
import java.util.List;

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

    @GetMapping("/student/list")
    public Iterable<Student> showStudentList() {
        return studentRepo.findAll();
    }

    @GetMapping("/course/list")
    public Iterable<Course> showCourseList() {
        return courseRepo.findAll();
    }
}
