package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.MarketNotFoundException;
import com.local.market.my_local_market.exceptions.StandNotFoundException;
import com.local.market.my_local_market.model.*;
import com.local.market.my_local_market.repository.mappers.MarketRowMapper;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
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
import static org.mockito.Mockito.when;

class StandDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplateMock;

    @InjectMocks
    private StandDao standDao;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllStandsTest() {
        Stand stand = new Stand();
        stand.setMarketID(1);
        stand.setProviderID(1);
        stand.setStandID(1);
        stand.setName("stand");

        when(jdbcTemplateMock.query(anyString(), any(StandRowMapper.class)))
                .thenReturn(new ArrayList<Stand>(Collections.singleton(stand)));

        List<Stand> stands = standDao.getAllStands();

        assertNotNull(stands);
        assertEquals(1, stands.size());
        assertEquals(1, stands.get(0).getStandID());
        assertEquals("stand", stands.get(0).getName());
        assertEquals(1, stands.get(0).getMarketID());
        assertEquals(1, stands.get(0).getProviderID());

    }

    @Test
    void createStandTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt(), anyInt(), anyString())).thenReturn(1);


        int rowsAffected = standDao.createStand(1, 1, "stand");


        assertEquals(1, rowsAffected);
    }

    @Test
    void getStandByIdTest() {
        Stand stand = new Stand();
        stand.setMarketID(1);
        stand.setProviderID(1);
        stand.setStandID(1);
        stand.setName("stand");

        when(jdbcTemplateMock.queryForObject(anyString(), any(StandRowMapper.class), anyInt()))
                .thenReturn(stand);

        Stand result = standDao.getStandById(1);

        assertNotNull(result);
        assertEquals(1, stand.getStandID());
        assertEquals(1, stand.getProviderID());
        assertEquals(1, stand.getMarketID());
        assertEquals("stand", stand.getName());


    }

    @Test
    void getStandByIdFailedTest() {
        when(jdbcTemplateMock.queryForObject(anyString(), any(StandRowMapper.class), anyInt()))
                .thenThrow(StandNotFoundException.class);


        assertThrows(StandNotFoundException.class, () -> standDao.getStandById(1));


    }

    @Test
    void deleteStandTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt())).thenReturn(1);

        int rowsAffected = standDao.deleteStand(1);

        assertEquals(1, rowsAffected);
    }

    @Test
    void getStockTest() {

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

        List<Product> result = standDao.getStock(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getProductID());
        assertEquals(1, result.get(0).getStandID());
        assertEquals(1, result.get(0).getProviderID());
        assertEquals(1.23F, result.get(0).getPrice());
        assertEquals(13, result.get(0).getAmount());
        assertEquals("product", result.get(0).getName());
        assertEquals(Type.Unknown, result.get(0).getTip());

    }

    @Test
    void getMarketByIdTest() {
        Market market = new Market();
        market.setName("Name");
        market.setDescription("Description");
        market.setMarketID(1);

        when(jdbcTemplateMock.queryForObject(anyString(), any(MarketRowMapper.class), anyInt()))
                .thenReturn(market);


        Market result = standDao.getMarketById(1);


        assertNotNull(result);
        assertEquals("Name", result.getName());
    }

    @Test
    void getMarketByIdFailedTest(){
        when(jdbcTemplateMock.queryForObject(anyString(), any(MarketRowMapper.class), anyInt()))
                .thenThrow(MarketNotFoundException.class);

        assertThrows(MarketNotFoundException.class, () -> standDao.getMarketById(1));
    }

    @Test
    void getAllStandsbyProviderIDTest() {
        Stand stand = new Stand();
        stand.setMarketID(1);
        stand.setProviderID(1);
        stand.setStandID(1);
        stand.setName("stand");

        when(jdbcTemplateMock.query(anyString(), any(StandRowMapper.class)))
                .thenReturn(new ArrayList<Stand>(Collections.singleton(stand)));


        List<Stand> stands = standDao.getAllStandsbyProviderID(1);

        assertNotNull(stands);
        assertEquals(1, stand.getStandID());
        assertEquals(1, stand.getProviderID());
        assertEquals(1, stand.getMarketID());
        assertEquals("stand", stand.getName());


    }
}