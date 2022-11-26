package ru.vdsimako.demo.arguments;

import org.junit.jupiter.params.provider.Arguments;
import ru.vdsimako.demo.provider.UserProvider;

import java.io.IOException;
import java.util.stream.Stream;

public class UserControllerTestArguments {

    public static Stream<Arguments> createUser_whenCreateUserDto_thenUserDto() throws IOException {
        return Stream.of(
                Arguments.arguments(
                        UserProvider.getCreateUserDtoRequest()
                )
        );
    }
}
