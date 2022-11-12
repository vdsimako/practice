package ru.vdsimako.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.model.entity.User;
import ru.vdsimako.demo.repository.UserRepository;
import ru.vdsimako.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDto createUser(CreateUserDto createUserDto) {

        User user = User.builder().build();

        User createdUser = userRepository.save(user);

//        вызов стороннего сервиса который что-то сохраняет в бд

        UserDto userDto = UserDto.builder()
                .email(createUserDto.email())
                .password(createUserDto.password())
                .build();

        return userDto;
    }
}
