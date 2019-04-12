package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.entity.Student;
import cc.conyli.restlearn.service.JmsMessageService;
import cc.conyli.restlearn.service.ReceiveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.JMSException;

@Controller
@RequestMapping("/message")
public class SendMessageController {

    private JmsMessageService jmsMessageService;
    private ReceiveMessageService receiveMessageService;

    @Autowired
    public SendMessageController(JmsMessageService jmsMessageService, ReceiveMessageService receiveMessageService) {
        this.receiveMessageService = receiveMessageService;
        this.jmsMessageService = jmsMessageService;
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

        jmsMessageService.sendStudentObejct(target);

        model.addAttribute("student", target);

        return "message";
    }

    @GetMapping("/receive")
    public String receiveMessage(Model model) throws JMSException {
        Student student = receiveMessageService.receiveStudent();
        model.addAttribute("res", student);

        return "message";
    }
}
