package com.windsor.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    // SELECT * FROM student WHERE name = ?
    List<Student> findByName(String name);

    // SELECT * FROM student WHERE name LIKE '%?'
    List<Student> findByNameEndingWith(String suffix);

    @Query(value = "SELECT id, name FROM student WHERE id = ?1 AND name = ?2", nativeQuery = true)
    Student useQuery(Integer id, String name);
}
