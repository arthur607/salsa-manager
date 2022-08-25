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

@Document(collection = "baixas")
@Getter
@Setter
public class Saida extends OperationModel {

    private BigDecimal totalValue;

    private Cliente cliente;

    public Saida(SaidaDto s) {
        super(s.getProducts().stream().map(Product::new).collect(Collectors.toUnmodifiableList()),s.getDataSaida());
        totalValue = totalProductsValue();
    }

    private BigDecimal totalProductsValue() {
        var v = new BigDecimal(0);
        for (Product p : super.getProducts()) {
            v = v.add(p.getUnitPrice());
        }
        return v;
    }
}
