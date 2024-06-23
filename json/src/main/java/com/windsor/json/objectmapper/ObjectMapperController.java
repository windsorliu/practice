package com.windsor.json.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectMapperController {

    @GetMapping("/convert")
    public String convert() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setName("Judy");
        user.setContactEmail("test.com");

        ObjectMapper objectMapper = new ObjectMapper();

        // User --> json
        String jsonResult = objectMapper.writeValueAsString(user);
        System.out.println("json value : " + jsonResult);


        // json --> User
        String json = "" +
                "{\"id\":3," +
                "\"name\":\"Amy\"," +
                "\"age\":20," +
                "\"contact_email\":\"hello@test.com\"}";

        User userResult = objectMapper.readValue(json, User.class);

        System.out.println("id : " + userResult.getId());
        System.out.println("name : " + userResult.getName());
        System.out.println("email : " + userResult.getContactEmail());

        return "convert success";
    }
}
