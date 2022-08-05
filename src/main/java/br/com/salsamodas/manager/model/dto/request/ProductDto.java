package br.com.salsamodas.manager.model.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductDto {
    private final int externalId;
    private final String color;
    private final String fornecedor;
    private final String category;
    private final String subcategory;
    private final BigDecimal unitPrice;

    private final boolean vendido = false;

    public ProductDto(int externalId, String color, String fornecedor, String category, String subcategory, BigDecimal unitPrice) {
        this.externalId = externalId;
        this.color = color;
        this.fornecedor = fornecedor;
        this.category = category;
        this.subcategory = subcategory;
        this.unitPrice = unitPrice;
    }
}
