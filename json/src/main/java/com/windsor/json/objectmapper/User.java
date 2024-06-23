package com.windsor.json.objectmapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// Include.NON_NULL，會將User為null的值，不顯示在轉換後的json字串中
@JsonInclude(JsonInclude.Include.NON_NULL)
// 如果json字串有多的key，直接忽視掉該key不轉換成java object
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer id;
    private String name;

    // 當json key的名稱跟java object名稱不同時
    @JsonProperty("contact_email")
    private String contactEmail;

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
