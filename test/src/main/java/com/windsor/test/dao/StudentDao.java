package com.windsor.test.dao;

import com.windsor.test.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StudentDao {

    Integer insert(Student student);

    void update(Student student);

    void deleteById(Integer id);

    Student getById(Integer id);

    List<Student> readParam(boolean graduate);
}
