package cc.conyli.restlearn.repository;

import cc.conyli.restlearn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface StudentRepo extends JpaRepository<Student, Integer> {

}
