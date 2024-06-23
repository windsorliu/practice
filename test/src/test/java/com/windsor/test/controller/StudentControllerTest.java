package com.windsor.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.test.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/students/{id}", 3)
                .header("headerName", "headerValue")
                .param("graduate", "true");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", equalTo(3)))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();

        System.out.println("Return response body: " + body);
    }

    @Test
    @Transactional
    public void create() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Student student = new Student();
        student.setName("Hank");
        student.setScore(14.6);
        student.setGraduate(false);

        String json = objectMapper.writeValueAsString(student);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }
}