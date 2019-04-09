package cc.conyli.restlearn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(force = true)
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    private final String courseName;


    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private final List<Student> students = new ArrayList<>();

    void add(Student student) {
        this.students.add(student);
    }

}
