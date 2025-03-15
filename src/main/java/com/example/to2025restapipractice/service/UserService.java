package com.example.to2025restapipractice.service;

import com.example.to2025restapipractice.dto.UserCreateRequest;
import com.example.to2025restapipractice.dto.UserResponse;
import com.example.to2025restapipractice.entity.User;
import com.example.to2025restapipractice.mapper.UserMapper;
import com.example.to2025restapipractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
//        return userRepository.findAll().stream()
//                .map(UserMapper.INSTANCE::toDto)
//                .toList();

        return UserMapper.INSTANCE.toDtoList(userRepository.findAll());
    }

    public UserResponse createUser(UserCreateRequest dto) {
        userRepository.findByEmail(dto.getEmail()).ifPresent(user -> {
            throw new RuntimeException("User already exists");
        });

        if (!Objects.equals(dto.getPass(), dto.getRePassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = userRepository.save(UserMapper.INSTANCE.toEntity(dto));
        return UserMapper.INSTANCE.toDto(user);
    }
}
