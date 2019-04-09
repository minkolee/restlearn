package cc.conyli.restlearn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
@NoArgsConstructor(force = true)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    private final String firstName;

    private final String lastName;

    //这里先不设置外键，否则JSON化之后会来回引用，无尽循环
    private final int courseId;

}
