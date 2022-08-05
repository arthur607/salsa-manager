package br.com.salsamodas.manager.model;

import br.com.salsamodas.manager.model.dto.EntradaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Document(collection = "entradas")
@NoArgsConstructor

public class Entrada {

    private List<Product> products;

    private LocalDateTime data_entrada;

    private int quantity;

    private GastoAdicional additionalInfo;

    private BigDecimal totalSpend;

    public Entrada(List<Product> products, LocalDateTime data_entrada) {
        this.products = products;
        this.data_entrada = data_entrada;
        this.quantity = this.products.size();
        this.totalSpend = totalProductsValue().add(totalAdditionalValues());
    }

    public Entrada(final EntradaDto e) {
        this.products = e.getProducts().stream().map(Product::new).collect(Collectors.toUnmodifiableList());
        this.data_entrada = e.getDataEntrada();
        this.quantity = this.products.size();
        if (e.getGastoAdicional() != null && e.getGastoAdicional().size() != 0) {
            this.additionalInfo = new GastoAdicional();
            this.additionalInfo.getGastoAdicional().putAll(e.getGastoAdicional());
        }
        this.totalSpend = totalProductsValue().add(totalAdditionalValues());
    }

    private BigDecimal totalProductsValue() {
        var v = new BigDecimal(0);
        for (Product p : this.products) {
            v = v.add(p.getUnitPrice());
        }
        return v;
    }

    private BigDecimal totalAdditionalValues() {
        var totalValue = new BigDecimal(0);
        if (this.additionalInfo != null) {
            final var m = this.additionalInfo.getGastoAdicional();
            for (Map.Entry<String, BigDecimal> entry : m.entrySet()) {
                totalValue = totalValue.add(entry.getValue());
            }
        }
        return totalValue;
    }
}
