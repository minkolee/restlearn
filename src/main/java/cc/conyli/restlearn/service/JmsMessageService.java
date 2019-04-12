package cc.conyli.restlearn.service;

import cc.conyli.restlearn.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class JmsMessageService implements JmsService {

    private JmsTemplate jmsTemplate;
    private Destination destination;

    @Autowired
    public JmsMessageService(JmsTemplate jmsTemplate, Destination destination) {
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
    }

    @Override
    public void sendStudentMessage(Student student) {
        jmsTemplate.send(destination, session -> {
            Message message = session.createObjectMessage(student);
            message.setStringProperty("ExtraInfo", "123");
            return message;});
    }

    @Override
    public void sendStudentObejct(Student student) {
        jmsTemplate.convertAndSend(destination, student, message -> {message.setStringProperty("ExtraInfo","123");
            return message;} );
    }




}
