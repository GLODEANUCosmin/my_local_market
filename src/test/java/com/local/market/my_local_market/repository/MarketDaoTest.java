package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.MarketNotFoundException;
import com.local.market.my_local_market.model.Market;
import com.local.market.my_local_market.repository.MarketDao;
import com.local.market.my_local_market.repository.mappers.MarketRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MarketDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplateMock;

    @InjectMocks
    private MarketDao marketDao;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void GetAllMarketsTest() {

        Market market = new Market();
        market.setName("Name");
        market.setDescription("Description");
        market.setMarketID(1);

        when(jdbcTemplateMock.query(anyString(), any(MarketRowMapper.class)))
                .thenReturn(new ArrayList<Market>(Collections.singleton(market)));


        List<Market> markets = marketDao.getAllMarkets();


        assertNotNull(markets);
        assertEquals(1, markets.size());
        assertEquals("Name", markets.get(0).getName());
        assertEquals("Description", markets.get(0).getDescription());
        assertEquals(1, markets.get(0).getMarketID());

    }

    @Test
    public void CreateMarketTest() {

        when(jdbcTemplateMock.update(anyString(), anyString(), anyString())).thenReturn(1);


        int rowsAffected = marketDao.createMarket("New Market", "New Description");


        assertEquals(1, rowsAffected);
    }

    @Test
    public void UpdateMarketNameTest() {

        when(jdbcTemplateMock.update(anyString(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = marketDao.updateMarketName("Updated Name", 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    public void UpdateMarketDescriptionTest() {

        when(jdbcTemplateMock.update(anyString(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = marketDao.updateMarketDescription("Updated Description", 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    public void GetMarketByIdTest() {

        Market market = new Market();
        market.setMarketID(1);
        market.setName("Name");
        market.setDescription("Description");
        when(jdbcTemplateMock.queryForObject(anyString(), any(MarketRowMapper.class), anyInt()))
                .thenReturn(market);


        Market result = marketDao.getMarketById(1);


        assertNotNull(result);
        assertEquals("Name", result.getName());
    }

    @Test
    public void GetMarketByIdNotFoundTest() {

        when(jdbcTemplateMock.queryForObject(anyString(), any(MarketRowMapper.class), anyInt()))
                .thenThrow(new EmptyResultDataAccessException(1));


        assertThrows(MarketNotFoundException.class, () -> marketDao.getMarketById(1));
    }

    @Test
    public void DeleteMarketTest() {

        when(jdbcTemplateMock.update(anyString(), anyInt())).thenReturn(1);


        int rowsAffected = marketDao.deleteMarket(1);

       
        assertEquals(1, rowsAffected);
    }


}