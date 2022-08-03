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
@Document(collection = "baixas")
@NoArgsConstructor
public class Saida {

    private List<Product> products;

    private LocalDateTime dataSaida;

    private int quantity;

    private BigDecimal totalValue;

    private Cliente cliente;
}
