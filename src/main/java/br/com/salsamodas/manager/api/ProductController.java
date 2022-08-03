package br.com.salsamodas.manager.api;

import br.com.salsamodas.manager.model.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final MongoOperations operations;

    public ProductController(MongoOperations operations) {
        this.operations = operations;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(Entrada entrada){
        var p = List.of(new Product(1,"rosa",new Fornecedor("Neha"),new Category("VESTIDO"
        ,new SubCategory("MARJORIE")),new BigDecimal("100.0")));
        var e = new Entrada(p, LocalDateTime.now());
        operations.save(e);
        e.getProducts().forEach(operations::save);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("sell")
    public ResponseEntity<Void> sellProduct(Saida saida){

        return ResponseEntity.ok().build();
    }
}
