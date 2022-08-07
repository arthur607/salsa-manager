package br.com.salsamodas.manager.repository

import br.com.salsamodas.manager.model.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<Product?, Long?>