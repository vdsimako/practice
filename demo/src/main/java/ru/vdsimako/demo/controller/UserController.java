package ru.vdsimako.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.service.IUserService;

@Log4j2
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final IUserService userService;
    private final IUserService anotherUserService;

    public UserController(@Qualifier("userServiceImpl") IUserService userService,
                          @Qualifier("anotherUSerServiceImpl")IUserService anotherUserService) {
        this.userService = userService;
        this.anotherUserService = anotherUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        log.info("Get request for create user, createUserDto={}", createUserDto);

        UserDto userDto = userService.createUser(createUserDto);

        log.info("Return response for create user, userDto={}", userDto);

        return userDto;
    }
}
