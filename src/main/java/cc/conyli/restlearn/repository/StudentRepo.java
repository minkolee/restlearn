package cc.conyli.restlearn.repository;

import cc.conyli.restlearn.entity.Student;
import org.springframework.data.repository.CrudRepository;



public interface StudentRepo extends CrudRepository<Student, Integer> {

}
