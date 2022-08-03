package br.com.salsamodas.manager;

import br.com.salsamodas.manager.model.Category;
import br.com.salsamodas.manager.model.Entrada;
import br.com.salsamodas.manager.model.Fornecedor;
import br.com.salsamodas.manager.model.Product;
import br.com.salsamodas.manager.model.SubCategory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
		List<Product> products = List.of(new Product(1, "azul",
				new Fornecedor("neha"), new Category("vestido", new SubCategory("marjorie")), new BigDecimal("100.0")));
		var a  = new Entrada(products,
				LocalDateTime.now());
		System.out.println(a);
	}
}
