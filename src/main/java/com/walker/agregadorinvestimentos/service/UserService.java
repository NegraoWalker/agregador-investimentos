package com.walker.agregadorinvestimentos.service;

import com.walker.agregadorinvestimentos.Dto.CreateUserDto;
import com.walker.agregadorinvestimentos.entity.User;
import com.walker.agregadorinvestimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    //Injeção de dependência via método construtor:
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){
        //DTO -> Entity:
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null
        );
        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }





}
