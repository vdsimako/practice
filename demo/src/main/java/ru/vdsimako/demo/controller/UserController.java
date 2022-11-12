package ru.vdsimako.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.service.IUserService;

@Log4j2
@RestController("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        log.info("Get request for create user, createUserDto={}", createUserDto);

        UserDto userDto = userService.createUser(createUserDto);

        log.info("Return response for create user, userDto={}", userDto);

        return userDto;
    }

    @PutMapping
    public UserDto updateUser() {
        return null;
    }

    @GetMapping("/{id}")
    public UserDto getUserDto(@PathVariable Long id) {
        return null;
    }

    @GetMapping
    public UserDto getUserDto(@RequestParam String email) {
        return null;
    }
}
