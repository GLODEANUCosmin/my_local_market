package com.local.market.my_local_market.controller;

import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.service.MarketService;
import com.local.market.my_local_market.service.ProductService;
import com.local.market.my_local_market.service.StandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
public class ProductController {

    private final ProductService productService;
    private final StandService standService;

    @Autowired
    public ProductController(ProductService productService, StandService standService) {
        this.productService = productService;
        this.standService = standService;
    }

    @PostMapping(value = "markets/{marketID}/stands/{standID}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerProduct(@RequestBody Product product) {
        productService.registerProduct(product);
    }

    @GetMapping(value = "markets/{marketID}/stands/{standID}/products/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping(value = "markets/{marketID}/stands/{standID}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts(@PathVariable int standID) {
        return standService.getStock(standID);
    }

    @PutMapping(value = "markets/{marketID}/stands/{standID}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @PatchMapping(value = "markets/{marketID}/stands/{standID}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void patchProduct(@PathVariable Integer id, @RequestBody Map<String, String> partialProduct) {
        productService.patchProduct(id, partialProduct);
    }

    @DeleteMapping(value = "markets/{marketID}/stands/{standID}/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}
