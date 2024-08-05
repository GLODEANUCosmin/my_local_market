package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.ProductNotFoundException;
import com.local.market.my_local_market.exceptions.ProviderNotFoundException;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Provider;
import com.local.market.my_local_market.model.Stand;
import com.local.market.my_local_market.model.Type;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import com.local.market.my_local_market.repository.mappers.ProviderRowMapper;
import com.local.market.my_local_market.repository.mappers.StandRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ProviderDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplateMock;

    @InjectMocks
    private ProviderDao providerDao;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllProvidersTest() {
        Provider provider = new Provider();
        provider.setId(1);
        provider.setRating(1);
        provider.setName("provider");
        provider.setPassword("password");
        provider.setCodCUI("123abc");
        provider.setWallet(100.25f);

        when(jdbcTemplateMock.query(anyString(), any(ProviderRowMapper.class)))
                .thenReturn(new ArrayList<Provider>(Collections.singleton(provider)));


        List<Provider> providers = providerDao.getAllProviders();

        assertNotNull(providers);
        assertEquals(1, providers.size());
        assertEquals(1, providers.get(0).getId());
        assertEquals("provider", providers.get(0).getName());
        assertEquals(100.25f, providers.get(0).getWallet());
        assertEquals("password", providers.get(0).getPassword());
        assertEquals(1, providers.get(0).getRating());
        assertEquals("123abc", providers.get(0).getCodCUI());
    }

    @Test
    void createProviderTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt(), anyString())).thenReturn(1);


        int rowsAffected = providerDao.createProvider(1, "123abc");


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateCodCUITest() {
        when(jdbcTemplateMock.update(anyString(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = providerDao.updateCodCUI( "123abc", 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateRatingTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt(), anyInt())).thenReturn(1);


        int rowsAffected = providerDao.updateRating( 1, 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void deleteProviderTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt())).thenReturn(1);


        int rowsAffected = providerDao.deleteProvider(  1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void getProviderByIdTest() {
        Provider provider = new Provider();
        provider.setId(1);
        provider.setRating(1);
        provider.setName("provider");
        provider.setPassword("password");
        provider.setCodCUI("123abc");
        provider.setWallet(100.25f);

        when(jdbcTemplateMock.queryForObject(anyString(), any(ProviderRowMapper.class), anyInt()))
                .thenReturn(provider);

        Provider result = providerDao.getProviderById(1);

        assertNotNull(result);
        assertEquals(1,result.getId());

    }

    @Test
    void getProviderByIdFailedTest() {
        when(jdbcTemplateMock.queryForObject(anyString(), any(ProviderRowMapper.class), anyInt()))
                .thenThrow(ProviderNotFoundException.class);


        assertThrows(ProviderNotFoundException.class, () -> providerDao.getProviderById(1));
    }

    @Test
    void getAllTheirStandsTest() {
        Stand stand = new Stand();
        stand.setMarketID(1);
        stand.setProviderID(1);
        stand.setStandID(1);
        stand.setName("stand");

        when(jdbcTemplateMock.query(anyString(), any(StandRowMapper.class), anyInt()))
                .thenReturn(new ArrayList<Stand>(Collections.singleton(stand)));


        List<Stand> stands = providerDao.getAllTheirStands(1);

        assertNotNull(stands);
        assertEquals(1, stands.size());
        assertEquals(1, stands.get(0).getStandID());
        assertEquals("stand", stands.get(0).getName());
        assertEquals(1, stands.get(0).getMarketID());
        assertEquals(1, stands.get(0).getProviderID());
    }

    @Test
    void getAllTheirStockTest() {
        Product product = new Product();
        product.setPrice(11.5f);
        product.setProductID(1);
        product.setAmount(12);
        product.setName("product");
        product.setTip(Type.Unknown);
        product.setStandID(1);
        product.setProviderID(1);

        when(jdbcTemplateMock.query(anyString(), any(ProductRowMapper.class), anyInt()))
                .thenReturn(new ArrayList<Product>(Collections.singleton(product)));


        List<Product> products = providerDao.getAllTheirStock(1);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(1, products.get(0).getStandID());
        assertEquals(1, products.get(0).getProductID());
        assertEquals(1, products.get(0).getProviderID());
        assertEquals("product", products.get(0).getName());
        assertEquals(Type.Unknown, products.get(0).getTip());
        assertEquals(12, products.get(0).getAmount());
        assertEquals(11.5f, products.get(0).getPrice());
    }

    @Test
    void updateProviderTest() {
        when(jdbcTemplateMock.update(anyString(), anyString(), anyInt(), anyInt())).thenReturn(1);


        int rowsAffected = providerDao.updateProvider(  "123abc", 1, 1);


        assertEquals(1, rowsAffected);

    }
}