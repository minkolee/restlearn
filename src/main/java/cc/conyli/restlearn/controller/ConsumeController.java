package cc.conyli.restlearn.controller;

import cc.conyli.restlearn.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/consume")
public class ConsumeController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/gettest")
    public String getTest() {
        Student student = getStudentById("3");
        System.out.println(student);

        student = getStudentByIdMap("5");
        System.out.println(student);

        student = getStudentByIdUrl("12");
        System.out.println(student);

        student = getStudentByIdFromResponseEntity("15");
        System.out.println(student);

        return "home";
    }

    @GetMapping("/puttest")
    public String putTest() {
        Student student = new Student();
        student.setFirstName("cony");
        student.setLastName("gugu");
        student.setCourseId(4);
        student.setId(11);

        updateStudent(student);
        return "home";
    }

    @GetMapping("/deltest")
    public String delTest() {
        Student student = getStudentById("10");
        deleteStudent(student);

        return "home";
    }

    @GetMapping("/posttest")
    public String postTest() {
        Student student = new Student();
        student.setCourseId(3);
        student.setLastName("Jenny");
        student.setFirstName("Huang");

        URI uri = postStudentAndGetAddress(student);
        System.out.println(uri);

        Student newStudent = postStudentByEntity(student);
        log.info("新添加的学生是：" + newStudent);

        return "home";
    }

    @GetMapping("/tranverson")
    public String trans() {
        Traverson traverson = new Traverson(URI.create("http://localhost:8080/rest"), MediaTypes.HAL_JSON);
        ParameterizedTypeReference<Resources<Student>> parameterizedTypeReference = new ParameterizedTypeReference<Resources<Student>>() {
        };
        Resources<Student> resources = traverson.follow("students").toObject(parameterizedTypeReference);
        Collection<Student> students = resources.getContent();
        log.info(students.toString());

        String url = traverson.follow("students").asLink().getHref();
        System.out.println(url);

        Student student = new Student();
        student.setCourseId(3);
        student.setLastName("Jenny");
        student.setFirstName("Huang");

        restTemplate.postForLocation(url, student);

        return "home";
    }








    private Student getStudentById(String id) {
        return restTemplate.getForObject("http://localhost:8080/myapi/students/{id}", Student.class, id);
    }

    private Student getStudentByIdMap(String studentId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", studentId);
        return restTemplate.getForObject("http://localhost:8080/myapi/students/{id}", Student.class, urlVariables);
    }

    private Student getStudentByIdUrl(String studentId) {
        Map<String,String> urlVariables = new HashMap<>();
        urlVariables.put("id", studentId);
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/myapi/students/{id}").build(urlVariables);
        return restTemplate.getForObject(url, Student.class);
    }

    private Student getStudentByIdFromResponseEntity(String studentId) {
        ResponseEntity<Student> responseEntity = restTemplate.getForEntity("http://localhost:8080/myapi/students/{id}", Student.class, studentId);
        log.info("Fetched time" + responseEntity.getStatusCode() + responseEntity.getStatusCodeValue() + responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    private void updateStudent(Student student) {
        restTemplate.put("http://localhost:8080/myapi/students/{id}", student, student.getId());
    }

    private void deleteStudent(Student student) {
        restTemplate.delete("http://localhost:8080/myapi/students/{id}",student.getId());
    }


    private void postStudent(Student student) {
        restTemplate.postForObject("http://localhost:8080/myapi/students", student, Student.class);
    }

    private URI postStudentAndGetAddress(Student student) {
        return restTemplate.postForLocation("http://localhost:8080/myapi/students", student);
    }

    private Student postStudentByEntity(Student student) {
        ResponseEntity<Student> responseEntity = restTemplate.postForEntity("http://localhost:8080/myapi/students", student, Student.class);
        log.info(responseEntity.getHeaders().getLocation()+" ");
        log.info(responseEntity.getStatusCode() + " 响应码");
        return responseEntity.getBody();
    }


}
