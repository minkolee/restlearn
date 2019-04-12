package cc.conyli.restlearn.service;

import cc.conyli.restlearn.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
public class RabbitReceiveService implements JmsReceiveService {

    private RabbitTemplate rabbitTemplate;

    private MessageConverter messageConverter;

    @Autowired
    public RabbitReceiveService(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    @Override
    public Student receiveStudent() throws JMSException {
        return (Student) rabbitTemplate.receiveAndConvert("sia5");
    }
}
