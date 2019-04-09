package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.entity.Course;
import cc.conyli.restlearn.entity.Student;
import cc.conyli.restlearn.repository.CourseRepo;
import cc.conyli.restlearn.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    private CourseRepo courseRepo;
    private StudentRepo studentRepo;

    @Autowired
    public WebController(CourseRepo courseRepo, StudentRepo studentRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }


    @GetMapping("/student/list")
    public String showStudentList(Model model) {
        model.addAttribute("students", studentRepo.findAll());

        return "studentList";
    }

    @GetMapping("/course/list")
    public String showCourseList(Model model) {
        model.addAttribute("courses", courseRepo.findAll());

        return "courseList";
    }

}
