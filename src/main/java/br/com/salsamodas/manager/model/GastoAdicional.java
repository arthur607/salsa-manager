package br.com.salsamodas.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Data
@AllArgsConstructor
public class GastoAdicional {

    private final Map<String, BigDecimal> gastoAdicional = new HashMap<>(5);

}
