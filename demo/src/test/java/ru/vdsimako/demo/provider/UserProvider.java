package ru.vdsimako.demo.provider;

import org.springframework.util.ResourceUtils;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.model.entity.User;

import java.io.IOException;
import java.nio.file.Files;

public class UserProvider {
    public static CreateUserDto getCreateUserDto() {
        return new CreateUserDto("test@mail.ru", "test_secret");
    }

    public static User getUser() {
        return User.builder()
                .id(1L)
                .email("test@mail.ru")
                .password("test_secret")
                .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                .id(1L)
                .email("test@mail.ru")
                .password("test_secret")
                .build();
    }

    public static String getCreateUserDtoRequest() throws IOException {
        return new String(Files.readAllBytes(
                ResourceUtils.getFile("classpath:request/create_user.json").toPath()
        )
        );
    }
}
