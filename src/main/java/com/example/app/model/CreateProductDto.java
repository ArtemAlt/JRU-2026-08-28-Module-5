package com.example.app.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateProductDto {

    @NotBlank(message = "Название продукта обязательно")
    @Size(min = 3, max = 100,
            message = "Название должно быть от 3 до 100 символов")
    private String name;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть положительной")
    @DecimalMax(value = "1000000.00",
            message = "Цена не может превышать 1 000 000")
    private Double price;

    @NotBlank(message = "SKU обязателен")
    @Pattern(regexp = "[A-Z0-9-]{5,20}",
            message = "SKU должен быть формата: 5-20 символов A-Z, 0-9, дефис")
    private String sku;

    @Size(max = 1000,
            message = "Описание не более 1000 символов")
    private String description;

    @Min(value = 0, message = "Количество не может быть отрицательным")
    private Integer stockQuantity;
}