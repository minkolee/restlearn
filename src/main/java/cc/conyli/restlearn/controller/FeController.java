package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.repository.CourseRepo;
import cc.conyli.restlearn.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fe")
public class FeController {

    private CourseRepo courseRepo;
    private StudentRepo studentRepo;

    @Autowired
    public FeController(CourseRepo courseRepo, StudentRepo studentRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }


    @GetMapping("/student/list")
    public String showStudentList(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "spa-student";
    }

    @GetMapping("/course/list")
    public String showCourseList(Model model) {
        model.addAttribute("courses", courseRepo.findAll());
        return "spa-course";
    }

}
