package br.com.salsamodas.manager.api;

import br.com.salsamodas.manager.model.dto.request.SaidaDto;
import br.com.salsamodas.manager.service.SaidaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@Slf4j
public class SaidaController {

    private final SaidaService service;

    @Autowired
    public SaidaController(SaidaService saidaService) {
        this.service = saidaService;
    }

    @PatchMapping("/sell")
    public ResponseEntity<Void> sellProduct(@RequestBody SaidaDto saida) {
        service.process(saida);
        log.info("Operação realizado com sucesso!");
        return ResponseEntity.ok().build();
    }
}
