package com.windsor.jdbc.dao;

import com.windsor.jdbc.entity.Student;
import com.windsor.jdbc.row.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Student> selectAll() {
        String sql = "SELECT id,name FROM student LIMIT 100";

        Map<String, Object> map = new HashMap<>();

        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());

        return list;
    }

    public Student selectById(Integer id) {
        String sql = "SELECT id,name FROM student WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public String insert(Student student) {
        String sql = "INSERT INTO student(name) VALUE (:name)";

        Map<String, Object> map = new HashMap<>();
        map.put("name", student.getName());

        namedParameterJdbcTemplate.update(sql, map);

        return "INSERT SUCCESS";
    }

    public String insertList(List<Student> list) {
        String sql = "INSERT INTO student(name) VALUE (:name)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("name", student.getName());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);

        return "BATCH INSERT SUCCESS";
    }

    public String update(Integer id, Student student) {
        String sql = "UPDATE student SET name = :name WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id); // 如果設成student.id() 代表id也是可以被更改的
        map.put("name", student.getName());

        namedParameterJdbcTemplate.update(sql, map);

        return "UPDATE SUCCESS";
    }

    public String updateList(List<Student> list) {
        String sql = "UPDATE student SET name = :name WHERE id = :id";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("id", student.getId());
            parameterSources[i].addValue("name", student.getName());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);

        return "BATCH UPDATE SUCCESS";
    }

    public String delete(Integer id) {
        String sql = "DELETE FROM student WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        namedParameterJdbcTemplate.update(sql, map);

        return "DELETE SUCCESS";
    }

    public String deleteList(List<Student> list) {
        String sql = "DELETE FROM student WHERE id = :id";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("id", student.getId());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);

        return "BATCH DELETE SUCCESS";
    }
}
