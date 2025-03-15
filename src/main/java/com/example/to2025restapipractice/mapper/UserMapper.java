package com.example.to2025restapipractice.mapper;

import com.example.to2025restapipractice.dto.UserCreateRequest;
import com.example.to2025restapipractice.dto.UserResponse;
import com.example.to2025restapipractice.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toDto(User user);

    @Mapping(target = "password", source = "pass")
    User toEntity(UserCreateRequest userCreateRequest);

    List<UserResponse> toDtoList(List<User> users);
}
