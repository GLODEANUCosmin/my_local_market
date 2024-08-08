package com.local.market.my_local_market.repository;

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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ProviderDaoIntegrationTest {
    DataSource dataSource;
    ProviderDao providerDao;

    @BeforeEach
    public void setUp(){
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("reset.sql")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        providerDao = new ProviderDao();
        providerDao.setDataSource(dataSource);
    }

    @Test
    void getAllProvidersTest() {
        List<Provider> providers = providerDao.getAllProviders();

        assertNotNull(providers);
        assertEquals(3, providers.size());
        assertEquals(1, providers.get(0).getId());
        assertEquals("User1", providers.get(0).getName());
        assertEquals(1.45f, providers.get(0).getWallet());
        assertEquals("Password1", providers.get(0).getPassword());
        assertEquals(0, providers.get(0).getRating());
        assertEquals("abc123", providers.get(0).getCodCUI());
        assertEquals("def456", providers.get(1).getCodCUI());
    }

    @Test
    void createProviderTest() {
        assertThrows(ProviderNotFoundException.class, ()->providerDao.getProviderById(4));
        providerDao.createProvider(4, "ijk098");
        assertEquals(500, providerDao.getProviderById(4).getWallet());
    }

    @Test
    void updateCodCUITest() {
        assertEquals("abc123", providerDao.getProviderById(1).getCodCUI());
        providerDao.updateCodCUI( "123abc", 1);
        assertEquals("123abc", providerDao.getProviderById(1).getCodCUI());
    }

    @Test
    void updateRatingTest() {
        assertEquals(0,providerDao.getProviderById(1).getRating());
        providerDao.updateRating( 1, 1);
        assertEquals(1,providerDao.getProviderById(1).getRating());
    }

    @Test
    void deleteProviderTest() {
        assertNotNull(providerDao.getProviderById(1));
        providerDao.deleteProvider(1);
        assertThrows(ProviderNotFoundException.class, ()->providerDao.getProviderById(1));
    }

    @Test
    void getProviderByIdTest() {

        Provider result = providerDao.getProviderById(1);

        assertNotNull(result);
        assertEquals(1,result.getId());

    }

    @Test
    void getProviderByIdFailedTest() {
        assertThrows(ProviderNotFoundException.class, () -> providerDao.getProviderById(90));
    }

    @Test
    void getAllTheirStandsTest() {
        List<Stand> stands = providerDao.getAllTheirStands(1);

        assertNotNull(stands);
        assertEquals(3, stands.size());
        assertEquals(1, stands.get(0).getStandID());
        assertEquals("apples@co", stands.get(0).getName());
        assertEquals(1, stands.get(0).getMarketID());
        assertEquals(1, stands.get(0).getProviderID());
    }

    @Test
    void getAllTheirStockTest() {
        List<Product> products = providerDao.getAllTheirStock(1);

        assertNotNull(products);
        assertEquals(7, products.size());
        assertEquals(1, products.get(0).getStandID());
        assertEquals(1, products.get(0).getProductID());
        assertEquals(1, products.get(0).getProviderID());
        assertEquals("Apples", products.get(0).getName());
        assertEquals(Type.Cores, products.get(0).getTip());
        assertEquals(30, products.get(0).getAmount());
        assertEquals(2.45f, products.get(0).getPrice());
    }

    @Test
    void updateProviderTest() {
        Provider provider=providerDao.getProviderById(1);

        assertNotNull(provider);
        assertEquals("abc123", provider.getCodCUI());
        assertEquals(0, provider.getRating());

        providerDao.updateProvider(  "123abc", 1, 1);

        provider=providerDao.getProviderById(1);

        assertEquals("123abc", provider.getCodCUI());
        assertEquals(1, provider.getRating());
    }

}
