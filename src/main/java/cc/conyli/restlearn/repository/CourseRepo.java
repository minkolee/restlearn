package cc.conyli.restlearn.repository;

import cc.conyli.restlearn.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface CourseRepo extends JpaRepository<Course, Integer> {
}
