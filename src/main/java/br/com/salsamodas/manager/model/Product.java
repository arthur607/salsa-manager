package br.com.salsamodas.manager.model;

import br.com.salsamodas.manager.model.dto.request.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "product")
@NoArgsConstructor
public class Product {

    private int externalId;
    private String color;
    private Fornecedor fornecedor;
    private Category category;
    private BigDecimal unitPrice;

    private boolean vendido = false;

    public Product(int externalId, String color, Fornecedor fornecedor, Category category, BigDecimal unitPrice) {
        this.externalId = externalId;
        this.color = color;
        this.fornecedor = fornecedor;
        this.category = category;
        this.unitPrice = unitPrice;
    }

    public Product(final ProductDto e) {
        this.externalId = e.getExternalId();
        this.color = e.getColor();
        this.fornecedor = new Fornecedor(e.getFornecedor());
        this.category = new Category(e.getCategory(),new SubCategory(e.getSubcategory()));
        this.unitPrice = e.getUnitPrice();
    }
}