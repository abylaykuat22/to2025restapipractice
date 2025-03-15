package com.example.to2025restapipractice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "DTO для получения информации о товаре")
public class ProductResponse {

    private Long id;

    @Schema(name = "Наименование товара")
    private String name;
    private Double price;
    private String status;
    private String code;
}
