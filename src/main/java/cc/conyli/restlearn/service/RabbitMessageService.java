package cc.conyli.restlearn.service;

import cc.conyli.restlearn.entity.Student;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessageService implements JmsService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    @Override
    public void sendStudentMessage(Student student) {
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties properties = new MessageProperties();
        Message message = messageConverter.toMessage(student, properties);
        rabbitTemplate.send("", "sia5", message);
    }

    @Override
    public void sendStudentObejct(Student student) {
        rabbitTemplate.convertAndSend("","sia5", student, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties properties = message.getMessageProperties();
                properties.setHeader("ExtraInfo", "123");
                return message;
            }
        });
    }
}
