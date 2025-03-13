package com.example.to2025restapipractice.repository;

import com.example.to2025restapipractice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


//    @Query("SELECT p FROM Product p WHERE p.price > :min AND p.price < :max")
    List<Product> findAllByPriceBetween(Double min, Double max);

    Optional<Product> findByCode(String code);
}
