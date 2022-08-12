package br.com.salsamodas.manager.model;

import br.com.salsamodas.manager.model.dto.request.SaidaDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Document(collection = "baixas")
@NoArgsConstructor
@Getter
@Setter
public class Saida extends OperationModel {

    @MongoId
    private String id = UUID.randomUUID().toString();
    private List<Product> products;

    private LocalDateTime dataSaida;

    private int quantity;

    private BigDecimal totalValue;

    private Cliente cliente;

    public Saida(SaidaDto s) {
        this.products = s.getProducts().stream().map(Product::new).collect(Collectors.toUnmodifiableList());
        this.dataSaida = s.getDataSaida();
        this.quantity = products.size();
        totalValue = totalProductsValue();
    }

    private BigDecimal totalProductsValue() {
        var v = new BigDecimal(0);
        for (Product p : this.products) {
            v = v.add(p.getUnitPrice());
        }
        return v;
    }
}
