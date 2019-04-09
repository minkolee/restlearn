package cc.conyli.restlearn.repository;

import cc.conyli.restlearn.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StudentRepo extends CrudRepository<Student, Integer> {

}
