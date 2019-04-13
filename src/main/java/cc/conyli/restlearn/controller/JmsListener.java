package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.entity.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class JmsListener {

//    @org.springframework.jms.annotation.JmsListener(destination = "192.168.100.100:61616")
//    public void receiveStudent(Student student) {
//        System.out.println(student);
//    }

    @RabbitListener(queues = {"sia5"})
    public void receiveRabbitStudent(Student student) {
        System.out.println(student);
    }
}
