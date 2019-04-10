package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.api.CourseResource;
import cc.conyli.restlearn.api.CourseResourceAssembler;
import cc.conyli.restlearn.entity.Course;
import cc.conyli.restlearn.repository.CourseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/myapi/courses", produces = "application/json")
@CrossOrigin("*")
public class CourseRestController {

    private CourseRepo courseRepo;

    @Autowired
    public CourseRestController(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @GetMapping
    public Resources<CourseResource> showCourseList() {
        List<Course> courseList = (List<Course>) courseRepo.findAll();

        List<CourseResource> courseResources = new CourseResourceAssembler().toResources(courseList);
        Resources<CourseResource> resourcesHATEOAS = new Resources<>(courseResources);

        resourcesHATEOAS.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CourseRestController.class).showCourseList()).withRel("courses"));
        return resourcesHATEOAS;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Optional<Course> course = courseRepo.findById(id);
        if (course.isPresent()) {
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/courses", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Course addCourse(@RequestBody Course course) {
        log.info(course.toString());
        return courseRepo.save(course);
    }


}
