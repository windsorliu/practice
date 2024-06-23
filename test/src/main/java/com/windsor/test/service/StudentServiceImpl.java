package com.windsor.test.service;


import com.windsor.test.dao.StudentDao;
import com.windsor.test.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Integer insert(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void deleteById(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public Student getById(Integer id) {
        return studentDao.getById(id);
    }

    @Override
    public List<Student> readParam(boolean graduate) {
        return studentDao.readParam(graduate);
    }

}
