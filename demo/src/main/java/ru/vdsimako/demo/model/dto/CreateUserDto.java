package ru.vdsimako.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserDto(@JsonProperty("email") String email,
                            @JsonProperty("password") String password) {
}