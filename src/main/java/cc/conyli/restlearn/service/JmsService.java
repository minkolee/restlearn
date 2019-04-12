package cc.conyli.restlearn.service;

import cc.conyli.restlearn.entity.Student;

public interface JmsService {

    public void sendStudentMessage(Student student);

    public void sendStudentObejct(Student student);
}
