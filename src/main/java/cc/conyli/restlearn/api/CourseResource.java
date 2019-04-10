package cc.conyli.restlearn.api;

import cc.conyli.restlearn.entity.Course;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.ArrayList;
import java.util.List;

@Relation(value = "course", collectionRelation = "courses")
public class CourseResource extends ResourceSupport {

    private final StudentResourceAssembler studentResourceAssembler = new StudentResourceAssembler();

    @Getter
    private String courseName;

    @Getter
    private List<StudentResource> students = new ArrayList<>();

    public CourseResource(Course course) {
        this.courseName = course.getCourseName();
        this.students = studentResourceAssembler.toResources(course.getStudents());
    }
}
