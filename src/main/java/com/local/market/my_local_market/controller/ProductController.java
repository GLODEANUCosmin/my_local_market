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
    public void registerProduct(@RequestBody Product product, @PathVariable Integer marketID, @PathVariable Integer standID) {
        product.setStandID(standID);
        productService.registerProduct(product);
    }

    @GetMapping(value = "markets/{marketID}/stands/{standID}/products/{productID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable Integer productID) {
        return productService.getProductById(productID);
    }

    @GetMapping(value = "markets/{marketID}/stands/{standID}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProductsByStandIDM(@PathVariable int standID) {
        return productService.getAllProductsByStandId(standID);
    }

    @PutMapping(value = "/markets/{marketID}/stands/{standID}/products/{productID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@PathVariable Integer productID, @PathVariable Integer standID, @RequestBody Product product) {
        product.setStandID(standID);
        productService.updateProduct(productID, product);
    }

    @PatchMapping(value = "markets/{marketID}/stands/{standID}/products/{productID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void patchProduct(@PathVariable Integer productID, @RequestBody Map<String, String> partialProduct) {
        //System.out.println(partialProduct);
        productService.patchProduct(productID, partialProduct);
    }

    @DeleteMapping(value = "markets/{marketID}/stands/{standID}/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @GetMapping(value = "providers/{providerID}/stands/{standID}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProductsByStandIDP(@PathVariable Integer standID) {
        return productService.getAllProductsByStandId(standID);
    }
    @GetMapping(value = "providers/{providerID}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProductsByProviderID(@PathVariable Integer providerID) {
        return productService.getAllProductsByProviderId(providerID);
    }
}
