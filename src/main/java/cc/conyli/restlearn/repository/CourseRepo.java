package cc.conyli.restlearn.repository;

import cc.conyli.restlearn.entity.Course;
import org.springframework.data.repository.CrudRepository;


public interface CourseRepo extends CrudRepository<Course, Integer> {
}
