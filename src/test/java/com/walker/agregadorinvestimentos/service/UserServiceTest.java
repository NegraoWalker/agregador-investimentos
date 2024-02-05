package com.walker.agregadorinvestimentos.service;

import com.walker.agregadorinvestimentos.Dto.CreateUserDto;
import com.walker.agregadorinvestimentos.entity.User;
import com.walker.agregadorinvestimentos.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Nested
    class createUser{
        @Test
        void shouldCreateUserWithSucess(){
            //Arrange
            var user = new User(UUID.randomUUID(),"test","test@email.com","test123", Instant.now(),null);
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            var input = new CreateUserDto("test","test@email.com","test123");
            //Act
            var output = userService.createUser(input);
            //Assert
            assertNotNull(output);

            var userCaptured = userArgumentCaptor.getValue();
            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }
        @Test
        void shouldThrowExceptionWhenErrorOccurs(){
            //Arrange
            doThrow(new RuntimeException()).when(userRepository).save(any());
            var input = new CreateUserDto("test","test@email.com","test123");
            //Act & Assert
            assertThrows(RuntimeException.class,()-> userService.createUser(input));

        }
    }

    @Nested
    class getUserById{

    }

}