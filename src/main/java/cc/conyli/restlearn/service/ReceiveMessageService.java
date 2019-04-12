package cc.conyli.restlearn.service;

import cc.conyli.restlearn.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class ReceiveMessageService implements JmsReceiveService {

    private JmsTemplate jmsTemplate;

    private MessageConverter messageConverter;

    private Destination destination;


    @Autowired
    public ReceiveMessageService(JmsTemplate jmsTemplate, MessageConverter messageConverter, Destination destination) {
        this.destination = destination;
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
    }

    @Override
    public Student receiveStudent() throws JMSException {
        Message message = jmsTemplate.receive(destination);
        return (Student) messageConverter.fromMessage(message);
    }
}
