package ru.vdsimako.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.model.entity.User;
import ru.vdsimako.demo.model.exception.DemoServiceException;
import ru.vdsimako.demo.model.exception.ExceptionMessage;
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

        if (!createUserDto.email().contains("@mail")) {
            throw new DemoServiceException(ExceptionMessage.EMAIL_FORMAT_MISMATCH);
        }

        User user = User.builder()
                .email(createUserDto.email())
                .password(createUserDto.password())
                .build();

        User createdUser = userRepository.save(user);

//        вызов стороннего сервиса который что-то сохраняет в бд

        UserDto userDto = UserDto.builder()
                .id(createdUser.getId())
                .email(createdUser.getEmail())
                .password(createdUser.getPassword())
                .build();

        return userDto;
    }
}
