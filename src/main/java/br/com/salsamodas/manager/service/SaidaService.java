package br.com.salsamodas.manager.service;

import br.com.salsamodas.manager.model.Product;
import br.com.salsamodas.manager.model.Saida;
import br.com.salsamodas.manager.model.dto.request.ProductDto;
import br.com.salsamodas.manager.model.dto.request.SaidaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

@Service
@Slf4j
public class SaidaService {

    private final MongoOperations repository;

    public SaidaService(final MongoOperations repository) {
        this.repository = repository;
    }


    public void process(final SaidaDto saida) {
        final var p = saida.getProducts();
        final var dbProducts = new LinkedList<Product>();
        p.forEach(e -> {
            final var q = new Query();
            final var dbProduct = findProductInDb(e, q);
            if (Objects.equals(dbProduct, null)) {
                throw new RuntimeException("Produto selecionado não foi encontrado!" + e.getExternalId());
            }
            if (dbProduct.isVendido()) {
                throw new RuntimeException("Produto já foi vendido:"+ dbProduct);
            }
            dbProduct.setVendido(true);
            dbProducts.add(dbProduct);
            repository.updateFirst(q, Update.update("vendido", true), Product.class);
        });
        log.info("Documentos encontrados: " + dbProducts.size());
        verifyDate(saida);

        final var s = new Saida(saida);
        s.setProducts(dbProducts);
        repository.save(s);
    }

    private void verifyDate(SaidaDto saida) {
        if (saida.getDataSaida() == null) {
            saida.setDataSaida(LocalDateTime.now());
        }
    }

    private Product findProductInDb(final ProductDto e, final Query q) {
        q.addCriteria(Criteria.where("externalId").is(e.getExternalId()));
        return repository.findOne(q, Product.class);
    }
}
