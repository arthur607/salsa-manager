package br.com.salsamodas.manager.model.dto.request;

import br.com.salsamodas.manager.model.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class SaidaDto {

    private final List<ProductDto> products = new LinkedList<>();

    @JsonProperty("data_saida")
    @Setter
    private LocalDateTime dataSaida;

    private Cliente cliente;

    private String status;

    @JsonProperty("additional_info")
    private final Map<String, String> additionalInfo = new HashMap<>(5);
}
