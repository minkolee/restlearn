package cc.conyli.restlearn.service;

import cc.conyli.restlearn.entity.Student;

import javax.jms.JMSException;

public interface JmsReceiveService {

    Student receiveStudent() throws JMSException;
}
