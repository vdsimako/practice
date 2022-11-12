package ru.vdsimako.demo.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vdsimako.demo.model.dto.CreateUserDto;
import ru.vdsimako.demo.model.dto.UserDto;
import ru.vdsimako.demo.model.entity.User;
import ru.vdsimako.demo.repository.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

//    private final UserRepository repository = mock(UserRepository.class);
//
//    private UserServiceImpl userService = new UserServiceImpl(repository);

    @Test
    void createUser() {
        CreateUserDto createUserDto = new CreateUserDto("test@mail.ru", "test_sercret");

        User user = User.builder()
                .id(1L)
                .email("test@mail.ru")
                .password("test_secret")
                .build();

        when(userRepository.save(any())).thenReturn(user);

        UserDto actualCreatedUser = userService.createUser(createUserDto);

        UserDto expectedCreatedUser = UserDto.builder()
                .id(1L)
                .email("test@mail.ru")
                .password("test_secret")
                .build();

        assertEquals("test message", expectedCreatedUser, actualCreatedUser);
    }
}