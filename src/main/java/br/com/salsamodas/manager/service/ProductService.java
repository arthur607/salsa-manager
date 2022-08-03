package br.com.salsamodas.manager.service;

import br.com.salsamodas.manager.model.Entrada;
import br.com.salsamodas.manager.model.Product;
import br.com.salsamodas.manager.model.Saida;
import br.com.salsamodas.manager.repository.ProductRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final MongoOperations operations;

    public ProductService(ProductRepository repository, MongoOperations operations) {
        this.repository = repository;
        this.operations = operations;
    }

//    public List<Product> addProducts(Entrada entrada){
//        entrada.
//    }
//
//    public List<Product> sellProducts(Saida entrada){
//
//    }

}

