package br.com.salsamodas.manager.model.dto;

import br.com.salsamodas.manager.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class EntradaDto {

    private List<ProductDto> products;

    @JsonProperty("data_entrada")
    @Setter
    private LocalDateTime dataEntrada;

    private final Map<String, BigDecimal> gastoAdicional = new HashMap<>();

}
