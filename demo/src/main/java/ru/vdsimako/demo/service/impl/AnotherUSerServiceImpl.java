package ru.vdsimako.demo.service.impl;

import org.springframework.stereotype.Service;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.service.IUserService;

@Service
public class AnotherUSerServiceImpl implements IUserService {
    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        return null;
    }
}
