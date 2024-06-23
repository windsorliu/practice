package com.windsor.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/{id}")
    public Student selectById(@PathVariable Integer id) {

        Student student = studentRepository.findById(id).orElse(null);

        return student;
    }

    @GetMapping
    public List<Student> selectAll() {

        List<Student> list = new ArrayList<>();

        Iterable<Student> iterable = studentRepository.findAll();

        for (Student student : iterable) {
            list.add(student);
        }

        return list;
    }

    @PostMapping
    public String insert(@RequestBody Student student) {

        studentRepository.save(student);

        return "INSERT SUCCESS";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id,
            @RequestBody Student student) {

        Student s = studentRepository.findById(id).orElse(null);

        if (s == null) {
            return "UPDATE FAIL, DATA NOT FOUND";
        }

        student.setId(id);
        studentRepository.save(student);

        return "UPDATE SUCCESS";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {

        studentRepository.deleteById(id);

        return "DELETE SUCCESS";
    }

    // SELECT * FROM student WHERE name = ?
    @GetMapping("/findByName/{name}")
    public List<Student> findByName(@PathVariable String name) {

        List<Student> list = studentRepository.findByName(name);

        return list;
    }

    // SELECT * FROM student WHERE name LIKE '%?'
    @GetMapping("/findByNameEndingWith/{suffix}")
    public List<Student> findByNameEndingWith(@PathVariable String suffix) {

        List<Student> list = studentRepository.findByNameEndingWith(suffix);

        return list;
    }

    // SELECT * FROM student WHERE id = ?1 AND name = ?2
    @GetMapping("/useQuery/{id}/{name}")
    public Student useQuery(@PathVariable Integer id,
            @PathVariable String name) {

        Student student = studentRepository.useQuery(id, name);

        return student;
    }
}
