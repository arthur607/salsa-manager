package br.com.salsamodas.manager.service;

import br.com.salsamodas.manager.model.Entrada;
import br.com.salsamodas.manager.model.Product;
import br.com.salsamodas.manager.model.dto.EntradaDto;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntradaService {

    private final MongoOperations repository;

    public EntradaService(MongoOperations repository) {
        this.repository = repository;
    }

    public void process(final EntradaDto entrada) {
        validateDate(entrada);
        final var e = new Entrada(entrada);
        saveProducts(e.getProducts());
        repository.save(e);
    }

    private void validateDate(final EntradaDto entrada) {
        if (entrada.getDataEntrada() == null) {
            entrada.setDataEntrada(LocalDateTime.now());
        }
    }

    @Async
    private void saveProducts(final List<Product> p) {
        p.forEach(repository::save);
    }
}
