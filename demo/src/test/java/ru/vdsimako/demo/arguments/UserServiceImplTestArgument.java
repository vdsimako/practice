package ru.vdsimako.demo.arguments;

import org.junit.jupiter.params.provider.Arguments;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.model.entity.User;
import ru.vdsimako.demo.model.exception.DemoServiceException;
import ru.vdsimako.demo.model.exception.ExceptionMessage;
import ru.vdsimako.demo.provider.UserProvider;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class UserServiceImplTestArgument {
    public static Stream<Arguments> createUser_whenCreateUserDto_thenUserDto() {
        return Stream.of(
                arguments(
                        UserProvider.getCreateUserDto(),
                        UserProvider.getUser(),
                        UserProvider.getUserDto()
                ),
                arguments(
                        new CreateUserDto("test1@mail.ru", "test_secret"),
                        User.builder()
                                .id(2L)
                                .email("test1@mail.ru")
                                .password("test_secret")
                                .build(),
                        UserDto.builder()
                                .id(2L)
                                .email("test1@mail.ru")
                                .password("test_secret")
                                .build()
                )
        );
    }

    public static Stream<Arguments> createUser_whenCreateUserDto_thenException() {
        return Stream.of(
                arguments(
                        new CreateUserDto("test1@test.ru", "test_secret"),
                        new DemoServiceException(ExceptionMessage.EMAIL_FORMAT_MISMATCH)
                )
        );
    }
}
