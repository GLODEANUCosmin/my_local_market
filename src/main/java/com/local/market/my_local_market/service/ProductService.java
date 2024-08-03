package com.local.market.my_local_market.service;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.repository.ProductDao;
import com.local.market.my_local_market.util.ProductUtil;
import jakarta.transaction.Transactional;
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
        productRepository.createProduct(product.getProviderID(), product.getPrice(), product.getAmount(),product.getStandID(),product.getName(),product.getTip());
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

    @Transactional
    public void patchProduct(Integer id, Map<String, String> partialProduct) {
        //System.out.println(partialProduct);
        Product product = new Product();

        productUtil.patchProduct(product, partialProduct);

        if(product.getPrice()!=null){productRepository.updateProductPrice(product.getPrice(),id);}
        if(product.getAmount()!=null){productRepository.updateProductAmount(product.getAmount(),id);}
        if(product.getName()!=null){productRepository.updateProductName(product.getName(),id);}
        if(product.getTip()!=null){productRepository.updateProductType(product.getTip(),id);}

    }

    public List<Product> getAllProductsByProviderId(Integer providerID){
        return productRepository.getProductsByProviderID(providerID);
    }

    public List<Product> getAllProductsByStandId(Integer standID){
        return productRepository.getProductsByStandID(standID);
    }


    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

}
