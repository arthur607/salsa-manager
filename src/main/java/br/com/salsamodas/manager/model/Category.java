package br.com.salsamodas.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Category {

    private String name;
    private SubCategory subCategory;
}
