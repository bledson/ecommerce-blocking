package br.com.bledson.ecommerce.controller;

import br.com.bledson.ecommerce.domain.Product;
import br.com.bledson.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ProductController.ENDPOINT)
@RestController
public class ProductController {
    public static final String ENDPOINT = "/products";
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void create(@RequestBody CreateProductCommand createProductCommand) {
        productService.create(createProductCommand);
    }
    
    @GetMapping
    public Iterable<Product> findAll() {
        return productService.findAll();
    }
}
