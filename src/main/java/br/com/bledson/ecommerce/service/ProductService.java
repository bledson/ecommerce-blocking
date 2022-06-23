package br.com.bledson.ecommerce.service;

import br.com.bledson.ecommerce.controller.CreateProductCommand;
import br.com.bledson.ecommerce.domain.Product;
import br.com.bledson.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(CreateProductCommand createProductCommand) {
        Product product = productRepository.save(new Product(createProductCommand.name(), createProductCommand.quantity()));
        createProductCommand.images().parallelStream().forEach(
            s -> {
                String[] stringParts = s.split("\\.");
                String fileExtension = stringParts[stringParts.length - 1];
                RestTemplate restTemplate = new RestTemplate();
                byte[] body = restTemplate.getForEntity(s, byte[].class).getBody();
                assert body != null;
                try {
                    Files.write(Paths.get(System.getProperty("java.io.tmpdir") + File.separator + product.getId() + "-" + UUID.randomUUID() + "." + fileExtension), body);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
}
