package br.com.salsamodas.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Document
public class Estoque {

    private final Map<Product, Integer> products = new LinkedHashMap<>(10);

}
