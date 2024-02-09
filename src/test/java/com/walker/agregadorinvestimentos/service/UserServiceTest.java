package com.walker.agregadorinvestimentos.service;

import com.walker.agregadorinvestimentos.Dto.CreateUserDto;
import com.walker.agregadorinvestimentos.entity.User;
import com.walker.agregadorinvestimentos.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class createUser{
        @Test
        void shouldCreateUserWithSuccess(){
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

        @Test
        @DisplayName("Should get user by id with success when optional is present")
        void shouldGetUserByIdWithSuccessWhenOptionalIsPresent(){
            //Arrange:
            var user = new User(UUID.randomUUID(),"test","test@email.com","test123", Instant.now(),null);
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            //Act:
            var output = userService.getUserById(user.getUserId().toString());
            //Assert:
            assertTrue(output.isPresent());
            assertEquals(user.getUserId(),uuidArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Should get user by id with success when optional is empty")
        void shouldGetUserByIdWithSuccessWhenOptionalIsEmpty(){
            //Arrange:
            var userId = UUID.randomUUID();
            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());
            //Act:
            var output = userService.getUserById(userId.toString());
            //Assert:
            assertTrue(output.isEmpty());
            assertEquals(userId,uuidArgumentCaptor.getValue());
        }
    }

    @Nested
    class listUsers{
        @Test
        void shouldReturnAllUsersWithSuccess(){
            //Arrange:
            var user = new User(UUID.randomUUID(),"test","test@email.com","test123", Instant.now(),null);
            var userList = List.of(user);
            doReturn(userList).when(userRepository).findAll();
            //Act:
            var output = userService.listUsers();
            //Assert:
            assertNotNull(output);
            assertEquals(userList.size(),output.size());
        }
    }

    @Nested
    class deleteById{
        @Test
        void shouldDeleteUserWithSuccess(){
            //Arrange:
            doReturn(true).when(userRepository).existsById(uuidArgumentCaptor.capture());
            doNothing().when(userRepository).deleteById(uuidArgumentCaptor.capture());

            var userId = UUID.randomUUID();
            //Act:
            userService.deleteById(userId.toString());
            //Assert:
            var idList = uuidArgumentCaptor.getAllValues();
            assertEquals(userId,idList.get(0));
            assertEquals(userId,idList.get(1));
        }
    }





}