package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.ProductNotFoundException;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Type;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ProductDaoIntegrationTest {
    DataSource dataSource;
    ProductDao productDao;

    @BeforeEach
    public void setUp(){
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("reset.sql")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productDao = new ProductDao();
        productDao.setDataSource(dataSource);
    }

    @Test
    void getAllProductsTest() {
        List<Product> products = productDao.getAllProducts();

        assertNotNull(products);
        assertEquals(20, products.size());
        assertEquals(1, products.get(0).getProductID());
        assertEquals(1, products.get(0).getStandID());
        assertEquals(1, products.get(0).getProviderID());
        assertEquals(2.45F, products.get(0).getPrice());
        assertEquals(30, products.get(0).getAmount());
        assertEquals("Apples",products.get(0).getName());
        assertEquals(Type.Cores, products.get(0).getTip());
    }

    @Test
    void createProductTest() {
        assertThrows(ProductNotFoundException.class, ()-> productDao.getProductById(21));

        productDao.createProduct(1, 1.5F, 22, 2, "product", Type.Unknown);

        Product product;
        product=productDao.getProductById(21);
        assertNotNull(product);
        assertEquals(21,product.getProductID());
        assertEquals(Type.Unknown,product.getTip());
        assertEquals("product",product.getName());
        assertEquals(22,product.getAmount());
        assertEquals(2,product.getStandID());
        assertEquals(1.5F,product.getPrice());
        assertEquals(1,product.getProviderID());
    }

    @Test
    void updateProductTest() {
        Product product;

        product = productDao.getProductById(5);

        assertEquals(7.89f,product.getPrice());
        assertEquals(23,product.getAmount());
        assertEquals("Large Apples",product.getName());

        productDao.updateProduct(1, 1.5F, 22, 2, "product", 5);

        product = productDao.getProductById(5);

        assertEquals(1.5F,product.getPrice());
        assertEquals(22,product.getAmount());
        assertEquals("product",product.getName());
    }

    @Test
    void updateProductNameTest() {
        Product product;

        product = productDao.getProductById(5);

        assertEquals("Large Apples",product.getName());

        productDao.updateProductName("product", 5);

        product = productDao.getProductById(5);

        assertEquals("product",product.getName());
    }

    @Test
    void updateProductPriceTest() {
        Product product;

        product = productDao.getProductById(7);

        assertEquals(6.66f,product.getPrice());

        productDao.updateProductPrice(1.23f, 7);

        product = productDao.getProductById(7);

        assertEquals(1.23f,product.getPrice());
    }

    @Test
    void updateProductAmountTest() {
        Product product;

        product=productDao.getProductById(8);

        assertEquals(10, product.getAmount());

        productDao.updateProductAmount(12, 8);

        product=productDao.getProductById(8);

        assertEquals(12, product.getAmount());
    }

    @Test
    void updateProductTypeTest() {
        Product product;

        product=productDao.getProductById(9);

        assertEquals(Type.Cores,product.getTip());

        productDao.updateProductType(Type.Unknown, 1);

        product=productDao.getProductById(9);

        assertEquals(Type.Cores,product.getTip());
    }

    @Test
    void deleteProductTest() {
        assertNotNull(productDao.getProductById(7));

        productDao.deleteProduct( 7);

        assertThrows(ProductNotFoundException.class, ()->productDao.getProductById(7));
    }

    @Test
    void getProductByIdTest() {
        Product result = productDao.getProductById(1);

        assertNotNull(result);
        assertEquals(1, result.getProductID());
        assertEquals("Apples", result.getName());
    }

    @Test
    void getProductByIdFailedTest() {
        assertThrows(ProductNotFoundException.class, () -> productDao.getProductById(69));
    }

    @Test
    void getProductsByStandIDTest() {
        List<Product> products = productDao.getProductsByStandID(1);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(1, products.get(0).getProductID());
        assertEquals(1, products.get(0).getStandID());
        assertEquals(1, products.get(0).getProviderID());
        assertEquals(2.45f, products.get(0).getPrice());
        assertEquals(30, products.get(0).getAmount());
        assertEquals("Apples",products.get(0).getName());
        assertEquals(Type.Cores, products.get(0).getTip());
    }


    @Test
    void getProductsByProviderIDTest() {
        List<Product> products = productDao.getProductsByProviderID(3);

        assertNotNull(products);
        assertEquals(5, products.size());
        assertEquals(16, products.get(0).getProductID());
        assertEquals(7, products.get(0).getStandID());
        assertEquals(3, products.get(0).getProviderID());
        assertEquals(10, products.get(0).getPrice());
        assertEquals(30, products.get(0).getAmount());
        assertEquals("Aluminium Pipes",products.get(0).getName());
        assertEquals(Type.NonEdible, products.get(0).getTip());
    }

}
