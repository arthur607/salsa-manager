package br.com.salsamodas.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "entradas")
@NoArgsConstructor

public class Entrada {

    private List<Product> products;

    private LocalDateTime data_entrada;

    private int quantity;

    private BigDecimal totalValue;

    public Entrada(List<Product> products, LocalDateTime data_entrada) {
        this.products = products;
        this.data_entrada = data_entrada;
        this.quantity = this.products.size();
        this.totalValue = totalValue();
    }

    private BigDecimal totalValue(){
        var v = new BigDecimal(0);
        for (Product p:this.products) {
            v = v.add(p.getUnitPrice());
        }
        return v;
    }
}
