package cc.conyli.restlearn.api;

import cc.conyli.restlearn.controller.StudentRestController;
import cc.conyli.restlearn.entity.Student;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;


public class StudentResourceAssembler extends ResourceAssemblerSupport<Student, StudentResource> {

    public StudentResourceAssembler() {
        super(StudentRestController.class, StudentResource.class);
    }

    @Override
    protected StudentResource instantiateResource(Student entity) {
        return new StudentResource(entity);
    }

    @Override
    public StudentResource toResource(Student student) {
        return createResourceWithId(student.getId(), student);
    }
}
