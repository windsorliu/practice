package com.windsor.test.dao;

import com.windsor.test.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentDaoImplTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void getById() {
        Student student=studentDao.getById(1);

        assertNotNull(student);
        assertEquals("Amy",student.getName());
        assertEquals(90.3,student.getScore());
        assertTrue(student.isGraduate());
        assertNotNull(student.getCreateDate());
    }

    @Test
    @Transactional
    public void deleteById() {
        studentDao.deleteById(1);

        Student student=studentDao.getById(1);
        assertNull(student);
    }

    @Test
    @Transactional
    public void insert() {
        Student student=new Student();
        student.setName("Kevin");
        student.setScore(66.2);
        student.setGraduate(true);

        Integer id= studentDao.insert(student);

        Student result=studentDao.getById(id);

        assertNotNull(result);
        assertEquals("Kevin",result.getName());
        assertEquals(66.2,result.getScore());
        assertTrue(result.isGraduate());
        assertNotNull(result.getCreateDate());
    }

    @Test
    @Transactional
    public void update() {
        Student student=studentDao.getById(1);
        student.setName("John");

        studentDao.update(student);

        Student result=studentDao.getById(1);

        assertNotNull(result);
        assertEquals("John",result.getName());
    }
}