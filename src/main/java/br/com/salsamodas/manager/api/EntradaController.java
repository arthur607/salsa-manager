package br.com.salsamodas.manager.api;

import br.com.salsamodas.manager.model.Saida;
import br.com.salsamodas.manager.model.dto.request.EntradaDto;
import br.com.salsamodas.manager.service.EntradaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@Slf4j
public class EntradaController {

    private final EntradaService service;

    @Autowired
    public EntradaController(EntradaService entradaService) {
        this.service = entradaService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody EntradaDto entrada) {

        service.process(entrada);
        log.info("Operação realizado com sucesso!");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("sell")
    public ResponseEntity<Void> sellProduct(Saida saida) {

        return ResponseEntity.ok().build();
    }
}
