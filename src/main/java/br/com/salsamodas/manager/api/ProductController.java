package br.com.salsamodas.manager.api;

import br.com.salsamodas.manager.model.Product;
import br.com.salsamodas.manager.model.Saida;
import br.com.salsamodas.manager.model.dto.EntradaDto;
import br.com.salsamodas.manager.service.EntradaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {

    private final EntradaService entradaService;

    @Autowired
    public ProductController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody EntradaDto entrada){

            entradaService.process(entrada);
         log.info("Operação realizado com sucesso!");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("sell")
    public ResponseEntity<Void> sellProduct(Saida saida){

        return ResponseEntity.ok().build();
    }
}
