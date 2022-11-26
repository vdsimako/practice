package ru.vdsimako.demo.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.model.entity.User;
import ru.vdsimako.demo.model.exception.DemoServiceException;
import ru.vdsimako.demo.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @DisplayName("createUser_whenCreateUserDto_thenUserDto")
    @ParameterizedTest(name = "[{index}]")
    @MethodSource("ru.vdsimako.demo.arguments.UserServiceImplTestArgument#createUser_whenCreateUserDto_thenUserDto")
    void createUser_whenCreateUserDto_thenUserDto(CreateUserDto createUserDto,
                                                  User user,
                                                  UserDto expectedCreatedUser) {
        when(userRepository.save(any())).thenReturn(user);

        UserDto actualCreatedUser = userService.createUser(createUserDto);

        assertEquals("expected and actual must be equal", expectedCreatedUser, actualCreatedUser);

        assertTrue("email must ends with mail.ru", createUserDto.email().endsWith("@mail.ru"));
    }


    @DisplayName("createUser_whenCreateUserDto_thenException")
    @ParameterizedTest(name = "[{index}]")
    @MethodSource("ru.vdsimako.demo.arguments.UserServiceImplTestArgument#createUser_whenCreateUserDto_thenException")
    void createUser_whenCreateUserDto_thenException(CreateUserDto createUserDto,
                                                    DemoServiceException expectedRuntimeException) {

        DemoServiceException actualRuntimeException = assertThrows(DemoServiceException.class,
                () -> userService.createUser(createUserDto));

        Assertions.assertEquals(expectedRuntimeException.getExceptionMessage(), actualRuntimeException.getExceptionMessage());
    }
}