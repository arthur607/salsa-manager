package br.com.salsamodas.manager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class OperationModel {

    @MongoId
    private final String id = UUID.randomUUID().toString();

    private List<Product> products;

    private LocalDateTime data_operacao;

    private int quantity;

    public OperationModel(final List<Product> products, final LocalDateTime data_operacao) {
        this.products = products;
        this.data_operacao = data_operacao;
        this.quantity = this.products.size();
    }
}
