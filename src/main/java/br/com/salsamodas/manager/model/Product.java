package br.com.salsamodas.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Stack;

@Data
@AllArgsConstructor
@Document(collection = "product")
@NoArgsConstructor
public class Product {
    
    private int externalId;
    private String color;
    private Fornecedor fornecedor;
    private Category category;
    private BigDecimal unitPrice;

}