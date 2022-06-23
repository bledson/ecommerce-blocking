package br.com.bledson.ecommerce.repository;

import br.com.bledson.ecommerce.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
