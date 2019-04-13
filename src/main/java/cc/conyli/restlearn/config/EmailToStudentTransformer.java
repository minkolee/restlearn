package cc.conyli.restlearn.config;

import cc.conyli.restlearn.entity.Student;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;

@Component
public class EmailToStudentTransformer extends AbstractMailMessageTransformer<String> {

    private final String SUBJECT_KEYWORDS = "Student add";

    @Override
    protected AbstractIntegrationMessageBuilder<String> doTransform(Message message) throws Exception {
        String mymessage = processPayload(message);
        return MessageBuilder.withPayload(mymessage);
    }

    private String processPayload(Message message) {
        try {
            String subject = message.getSubject();
            if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
                String email =
                        ((InternetAddress) message.getFrom()[0]).getAddress();
                String content = message.getContent().toString();
                return content;
            }
        } catch (MessagingException e) {
        } catch (IOException e) {}
        return null;
    }
}
