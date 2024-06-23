package com.windsor.test.controller;

import com.windsor.test.model.Student;
import com.windsor.test.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final static Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student,
            @RequestHeader(required = false) String header) {

        if (header != null) {
            System.out.println("header : " + header);
        }

        Integer id = studentService.insert(student);

        Student newStudent = studentService.getById(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Student student) {

        student.setId(id);
        studentService.update(student);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        studentService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> read(@PathVariable Integer id) {

        // log.info("JOJO get student {}",id);
        // log.warn("i am warn");
        // log.error("i am error");
        // log.info("student : "+id);

        Student student = studentService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> readParam(@RequestParam boolean graduate,
            @RequestHeader(required = false) String header) {
        if (header != null) {
            System.out.println("header : " + header);
        }

        List<Student> list = studentService.readParam(graduate);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
