package br.com.salsamodas.manager.service;

import br.com.salsamodas.manager.model.Product;
import br.com.salsamodas.manager.model.Saida;
import br.com.salsamodas.manager.model.dto.request.SaidaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Service
@Slf4j
public class SaidaService {

    private final MongoOperations repository;

    public SaidaService(final MongoOperations repository) {
        this.repository = repository;
    }


    public void process(final SaidaDto saida) {
        final var p = saida.getProducts();
        final var dbProducts = new LinkedList<>();
        p.forEach(e -> {
            final var q = new Query();
            q.addCriteria(Criteria.where("externalId").is(e.getExternalId()));
            dbProducts.addAll(repository.find(q, Product.class));
        });
        assert dbProducts.size() != 0;
        log.info("Documentos encontrados: " + dbProducts.size());
        if (saida.getDataSaida() == null) {
            saida.setDataSaida(LocalDateTime.now());
        }
        var s = new Saida(saida);
        repository.save(s);
    }
}
