package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.ProductNotFoundException;
import com.local.market.my_local_market.exceptions.UserNotFoundException;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Type;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import com.local.market.my_local_market.repository.mappers.UserRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ProductDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplateMock;

    @InjectMocks
    private ProductDao productDao;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductsTest() {
        Product product = new Product();
        product.setProductID(1);
        product.setStandID(1);
        product.setProviderID(1);
        product.setPrice(1.23F);
        product.setAmount(13);
        product.setName("product");
        product.setTip(Type.Unknown);

        when(jdbcTemplateMock.query(anyString(), any(ProductRowMapper.class)))
                .thenReturn(new ArrayList<Product>(Collections.singleton(product)));


        List<Product> products = productDao.getAllProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(1, products.get(0).getProductID());
        assertEquals(1, products.get(0).getStandID());
        assertEquals(1, products.get(0).getProviderID());
        assertEquals(1.23F, products.get(0).getPrice());
        assertEquals(13, products.get(0).getAmount());
        assertEquals("product",products.get(0).getName());
        assertEquals(Type.Unknown, products.get(0).getTip());
    }

    @Test
    void createProductTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt(), anyFloat(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(1);


        int rowsAffected = productDao.createProduct(1, 1.5F, 22, 2, "product", Type.Unknown);


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateProductTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt(), anyFloat(), anyInt(), anyInt(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = productDao.updateProduct(1, 1.5F, 22, 2, "product", 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateProductNameTest() {
        when(jdbcTemplateMock.update(anyString(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = productDao.updateProductName("product", 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateProductPriceTest() {
        when(jdbcTemplateMock.update(anyString(), anyFloat(), anyInt())).thenReturn(1);


        int rowsAffected = productDao.updateProductPrice(1.23f, 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateProductAmountTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt(), anyInt())).thenReturn(1);


        int rowsAffected = productDao.updateProductAmount(12, 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateProductTypeTest() {
        when(jdbcTemplateMock.update(anyString(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = productDao.updateProductType(Type.Unknown, 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void deleteProductTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt())).thenReturn(1);


        int rowsAffected = productDao.deleteProduct( 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void getProductByIdTest() {
        Product product = new Product();
        product.setProductID(1);
        product.setStandID(1);
        product.setProviderID(1);
        product.setPrice(1.23F);
        product.setAmount(13);
        product.setName("product");
        product.setTip(Type.Unknown);

        when(jdbcTemplateMock.queryForObject(anyString(), any(ProductRowMapper.class), anyInt()))
                .thenReturn(product);

        Product result = productDao.getProductById(1);

        assertNotNull(result);
        assertEquals(1, product.getProductID());

    }

    @Test
    void getProductByIdFailedTest() {
        when(jdbcTemplateMock.queryForObject(anyString(), any(ProductRowMapper.class), anyInt()))
                .thenThrow(new EmptyResultDataAccessException(1));


        assertThrows(ProductNotFoundException.class, () -> productDao.getProductById(1));
    }

    @Test
    void getProductsByStandIDTest() {
        Product product = new Product();
        product.setProductID(1);
        product.setStandID(1);
        product.setProviderID(1);
        product.setPrice(1.23F);
        product.setAmount(13);
        product.setName("product");
        product.setTip(Type.Unknown);

        when(jdbcTemplateMock.query(anyString(), any(ProductRowMapper.class), anyInt()))
                .thenReturn(new ArrayList<Product>(Collections.singleton(product)));


        List<Product> products = productDao.getProductsByStandID(1);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(1, products.get(0).getProductID());
        assertEquals(1, products.get(0).getStandID());
        assertEquals(1, products.get(0).getProviderID());
        assertEquals(1.23F, products.get(0).getPrice());
        assertEquals(13, products.get(0).getAmount());
        assertEquals("product",products.get(0).getName());
        assertEquals(Type.Unknown, products.get(0).getTip());
    }


    @Test
    void getProductsByProviderIDTest() {
        Product product = new Product();
        product.setProductID(1);
        product.setStandID(1);
        product.setProviderID(1);
        product.setPrice(1.23F);
        product.setAmount(13);
        product.setName("product");
        product.setTip(Type.Unknown);

        when(jdbcTemplateMock.query(anyString(), any(ProductRowMapper.class), anyInt()))
                .thenReturn(new ArrayList<Product>(Collections.singleton(product)));


        List<Product> products = productDao.getProductsByProviderID(1);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(1, products.get(0).getProductID());
        assertEquals(1, products.get(0).getStandID());
        assertEquals(1, products.get(0).getProviderID());
        assertEquals(1.23F, products.get(0).getPrice());
        assertEquals(13, products.get(0).getAmount());
        assertEquals("product",products.get(0).getName());
        assertEquals(Type.Unknown, products.get(0).getTip());
    }


}