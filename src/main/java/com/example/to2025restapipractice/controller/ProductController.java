package com.example.to2025restapipractice.controller;

import com.example.to2025restapipractice.dto.ProductResponse;
import com.example.to2025restapipractice.entity.Product;
import com.example.to2025restapipractice.exception.EntityNotFoundException;
import com.example.to2025restapipractice.exception.IncorrectStatusException;
import com.example.to2025restapipractice.exception.ProductUniqueException;
import com.example.to2025restapipractice.mapper.ProductMapper;
import com.example.to2025restapipractice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) Double minPrice,
                                     @RequestParam(required = false) Double maxPrice) {
        return productService.getProducts(minPrice, maxPrice);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product newProduct = productService.createProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (ProductUniqueException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение товара по ID", description = "Возвращает товар из БД по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Товар не найден", content = @Content(schema = @Schema(implementation = EntityNotFoundException.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере, обратитесь к администратору", content = @Content(schema = @Schema(implementation = IncorrectStatusException.class))),
            @ApiResponse(responseCode = "200", description = "Товар найден", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))
            })
    })
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        try {
            ProductResponse product = ProductMapper.INSTANCE.toDto(productService.getProductById(id));
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id,
                                                 @RequestParam String status) {
        try {
            productService.updateStatus(id, status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IncorrectStatusException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
