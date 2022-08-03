package br.com.salsamodas.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Document
@NoArgsConstructor
public class Estoque {

    private static List<Product> products;

    private static int quantity = products.size();

    public static void adicionar(Product p){
        products.add(p);
    }

    public static void adicionar(Collection<Product> p){
        products.addAll(p);
    }

    public static void remover(Product p){
        products.remove(p);
    }

    public static void remover(Collection<Product> p){
        products.removeAll(p);
    }

}
