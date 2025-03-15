package com.example.to2025restapipractice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {

    private String email;
//    @JsonProperty("full_name") используется если только 1 вариант названия поля
    @JsonAlias({"full_name", "name"})
    private String fullName;
    private String pass;
    private String rePassword;
}
