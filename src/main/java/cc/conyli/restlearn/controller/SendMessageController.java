package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.entity.Student;
import cc.conyli.restlearn.service.JmsMessageService;
import cc.conyli.restlearn.service.RabbitMessageService;
import cc.conyli.restlearn.service.RabbitReceiveService;
import cc.conyli.restlearn.service.ReceiveMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.JMSException;

@Controller
@RequestMapping("/message")
public class SendMessageController {

//    JMS的Service
//    private JmsMessageService jmsMessageService;
//    private ReceiveMessageService receiveMessageService;
//
//    @Autowired
//    public SendMessageController(JmsMessageService jmsMessageService, ReceiveMessageService receiveMessageService) {
//        this.receiveMessageService = receiveMessageService;
//        this.jmsMessageService = jmsMessageService;
//    }

    private RabbitMessageService rabbitMessageService;
    private RabbitReceiveService rabbitReceiveService;


    @Autowired
    public SendMessageController(RabbitMessageService rabbitMessageService, RabbitReceiveService rabbitReceiveService) {
        this.rabbitMessageService = rabbitMessageService;
        this.rabbitReceiveService = rabbitReceiveService;
    }


    @GetMapping("/test")
    public String  showPage() {
        return "message";
    }

    @GetMapping("/send")
    public String sendMessage(Model model) {
        Student target = new Student();
        target.setFirstName("Jenny");
        target.setLastName("Huang");
        target.setCourseId(3);
        target.setId(666);

        rabbitMessageService.sendStudentObejct(target);

        model.addAttribute("student", target);

        return "message";
    }

    @GetMapping("/receive")
    public String receiveMessage(Model model) throws JMSException {
        Student student = rabbitReceiveService.receiveStudent();
        model.addAttribute("res", student);

        return "message";
    }
}
