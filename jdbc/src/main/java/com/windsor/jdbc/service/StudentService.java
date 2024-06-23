package com.windsor.jdbc.service;

import com.windsor.jdbc.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> selectAll();

    Student selectById(Integer id);

    String insert(Student student);

    String insertList(List<Student> list);

    String update(Integer id, Student student);

    String updateList(List<Student> list);

    String delete(Integer id);

    String deleteList(List<Student> list);
}
