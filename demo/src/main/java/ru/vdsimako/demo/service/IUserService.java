package ru.vdsimako.demo.service;

import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;

public interface IUserService {

    UserDto createUser(CreateUserDto createUserDto);
}
