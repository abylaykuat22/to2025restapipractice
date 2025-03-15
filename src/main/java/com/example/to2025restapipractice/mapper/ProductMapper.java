package com.example.to2025restapipractice.mapper;

import com.example.to2025restapipractice.dto.ProductResponse;
import com.example.to2025restapipractice.dto.UserCreateRequest;
import com.example.to2025restapipractice.dto.UserResponse;
import com.example.to2025restapipractice.entity.Product;
import com.example.to2025restapipractice.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse toDto(Product product);
}
