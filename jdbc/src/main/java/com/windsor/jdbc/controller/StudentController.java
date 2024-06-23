package com.windsor.jdbc.controller;

import com.windsor.jdbc.entity.Student;
import com.windsor.jdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> selectAll() {
        return studentService.selectAll();
    }

    @GetMapping("/{id}")
    public Student selectById(@PathVariable Integer id) {
        return studentService.selectById(id);
    }

    @PostMapping
    public String insert(@RequestBody Student student) {
        return studentService.insert(student);
    }

    @PostMapping("/batch/insert")
    public String insertList(@RequestBody List<Student> list) {
        return studentService.insertList(list);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @PutMapping("/batch/update")
    public String updateList(@RequestBody List<Student> list) {
        return studentService.updateList(list);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return studentService.delete(id);
    }

    @PostMapping("/batch/delete")
    public String deleteList(@RequestBody List<Student> list) {
        return studentService.deleteList(list);
    }
}
