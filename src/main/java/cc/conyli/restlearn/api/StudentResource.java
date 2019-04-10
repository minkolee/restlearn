package cc.conyli.restlearn.api;

import cc.conyli.restlearn.entity.Student;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class StudentResource extends ResourceSupport {

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private Integer courseId;

    public StudentResource(Student student) {
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.courseId = student.getCourseId();
    }
}
