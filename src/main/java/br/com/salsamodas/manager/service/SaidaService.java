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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class SaidaService {

    private final MongoOperations repository;

    public SaidaService(final MongoOperations repository) {
        this.repository = repository;
    }

    @Transactional
    public void process(final SaidaDto saida) {
        final var p = saida.getProducts();
        final var dbProducts = new LinkedList<Product>();
        p.forEach(e -> {
            final var q = new Query();
            final var dbProduct = findProductInDb(e, q);
            if (dbProduct.isVendido()) {
                throw new RuntimeException("Produto já foi vendido:"+ dbProduct);
            }
            dbProduct.setVendido(true);
            final var s = new Saida(saida);
            dbProduct.getOperacoes().add(s);
            dbProducts.add(dbProduct);
            verifyDate(saida);

            s.setProducts(dbProducts);
            repository.save(s);
            final var update = new Update();
            update.push("operacoes",s).set("vendido",true);
            repository.updateFirst(q, update, Product.class);
        });
        log.info("Documentos encontrados: " + dbProducts.size());

    }

    private void verifyDate(SaidaDto saida) {
        if (saida.getDataSaida() == null) {
            saida.setDataSaida(LocalDateTime.now());
        }
    }

    private Product findProductInDb(final ProductDto e, final Query q) {
        q.addCriteria(Criteria.where("externalId").is(e.getExternalId()));
        return Optional.ofNullable(repository.findOne(q, Product.class)).orElseThrow(()
                -> new RuntimeException("Produto selecionado não foi encontrado!" + e.getExternalId()));
    }
}
