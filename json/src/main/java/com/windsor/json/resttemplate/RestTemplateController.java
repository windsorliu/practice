package com.windsor.json.resttemplate;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestTemplateController {

    @GetMapping("/getForObject")
    public Student getForObject() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/students/5";

        Student student = restTemplate.getForObject(url, Student.class);

        return student;
    }

    @GetMapping("/getForObjectWithParam")
    public Student[] getForObjectWithParam() {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", true);

        String url = "http://localhost:8080/students?graduate={graduate}";

        Student[] student = restTemplate.getForObject(url, Student[].class, queryParamMap);

        return student;
    }

    @GetMapping("/getForEntity")
    public Student getForEntity() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/students/7";

        ResponseEntity<Student> studentEntity = restTemplate.getForEntity(url, Student.class);

        System.out.println("http狀態碼為：" + studentEntity.getStatusCode());

        return studentEntity.getBody();
    }

    @GetMapping("/postForObject")
    public Student postForObject() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("json_project_postForObject");

        String url = "http://localhost:8080/students";

        Student student = restTemplate.postForObject(url, studentRequestBody, Student.class);

        return student;
    }

    @GetMapping("/postForEntity")
    public Student postForEntity() {
        RestTemplate restTemplate = new RestTemplate();

        Student studentRequestBody = new Student();
        studentRequestBody.setName("json_project_postForEntity");

        String url = "http://localhost:8080/students";

        ResponseEntity<Student> responseEntity = restTemplate.postForEntity(
                url, studentRequestBody, Student.class);

        System.out.println("http狀態碼為：" + responseEntity.getStatusCode());

        return responseEntity.getBody();

    }

    @GetMapping("/exchangeGet")
    public Student[] exchangeGet() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("header", "exchangeGet");

        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        Map<String, Object> queryParamMap = new HashMap<>();
        queryParamMap.put("graduate", false);

        String url = "http://localhost:8080/students?graduate={graduate}";

        ResponseEntity<Student[]> getStudentEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                Student[].class,
                queryParamMap
        );

        System.out.println("http狀態碼為：" + getStudentEntity.getStatusCode());

        Student[] student = getStudentEntity.getBody();

        return student;
    }

    @GetMapping("/exchangePost")
    public Student exchangePost() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("header", "exchangePost");
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        Student studentRequestBody = new Student();
        studentRequestBody.setName("json_project_exchangePost");

        HttpEntity requestEntity = new HttpEntity(studentRequestBody, requestHeaders);

        String url = "http://localhost:8080/students";

        ResponseEntity<Student> postStudentEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Student.class
        );

        System.out.println("http狀態碼為：" + postStudentEntity.getStatusCode());

        Student student = postStudentEntity.getBody();

        return student;
    }
}
