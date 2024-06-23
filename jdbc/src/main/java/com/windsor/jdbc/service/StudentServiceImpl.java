package com.windsor.jdbc.service;

import com.windsor.jdbc.dao.StudentDao;
import com.windsor.jdbc.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> selectAll() {
        return studentDao.selectAll();
    }

    public Student selectById(Integer id) {
        return studentDao.selectById(id);
    }

    public String insert(Student student) {
        return studentDao.insert(student);
    }

    public String insertList(List<Student> list) {
        return studentDao.insertList(list);
    }

    public String update(Integer id, Student student) {
        return studentDao.update(id, student);
    }

    public String updateList(List<Student> list) {
        return studentDao.updateList(list);
    }

    public String delete(Integer id) {
        return studentDao.delete(id);
    }

    public String deleteList(List<Student> list) {
        return studentDao.deleteList(list);
    }
}
