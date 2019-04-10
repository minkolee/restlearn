package cc.conyli.restlearn.api;

import cc.conyli.restlearn.controller.CourseRestController;
import cc.conyli.restlearn.controller.StudentRestController;
import cc.conyli.restlearn.entity.Course;
import cc.conyli.restlearn.entity.Student;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;


public class CourseResourceAssembler extends ResourceAssemblerSupport<Course, CourseResource> {

    public CourseResourceAssembler() {
        super(CourseRestController.class, CourseResource.class);
    }

    @Override
    protected CourseResource instantiateResource(Course entity) {
        return new CourseResource(entity);
    }

    @Override
    public CourseResource toResource(Course course) {
        return createResourceWithId(course.getId(), course);
    }
}
