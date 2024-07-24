package com.local.market.my_local_market.service;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.repository.ProductDao;
import com.local.market.my_local_market.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ProductService {
    private final ProductDao productRepository;
    private final ProductUtil productUtil;

    @Autowired
    public ProductService(ProductDao productRepository, ProductUtil productUtil) {
        this.productRepository = productRepository;
        this.productUtil = productUtil;
    }


    public void registerProduct(Product product) {
        productRepository.createProduct(product.getProviderID(), product.getPrice(), product.getAmount(),product.getStandID(),product.getName());
    }


    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }


    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }


    public void updateProduct(Integer id, Product product) {
        productRepository.updateProduct(product.getProviderID(),product.getPrice(),product.getAmount(),product.getStandID(),product.getName(), id);
    }


    public void patchProduct(Integer id, Map<String, String> partialProduct) {
        Product product = productRepository.getProductById(id);

        productUtil.patchProduct(product, partialProduct);

        productRepository.updateProduct(product.getProviderID(),product.getPrice(),product.getAmount(),product.getStandID(),product.getName(), id);
    }


    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

}
