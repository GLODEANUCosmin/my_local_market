package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.MarketNotFoundException;
import com.local.market.my_local_market.model.Market;
import com.local.market.my_local_market.repository.mappers.MarketRowMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class MarketDaoIntegrationTest {

    DataSource dataSource;
    MarketDao marketDao;

    @BeforeEach
    public void setUp(){
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)

                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        marketDao = new MarketDao();
        marketDao.setDataSource(dataSource);
    }

    @Test
    public void getAllMarketsTest() {
        List<Market> results = marketDao.getAllMarkets();

        assertEquals(4, results.size());
        assertEquals(1, results.get(0).getMarketID());
        assertEquals(3, results.get(2).getMarketID());
        assertEquals("Market4", results.get(3).getName());

    }

    @Test
    public void CreateMarketTest() {
        int rowsAffected = marketDao.createMarket("New Market", "New Description");

        System.out.println(marketDao.getMarketById(5).getName() + ' ' + marketDao.getMarketById(5).getDescription());

        assertEquals(1, rowsAffected);
    }

    @Test
    public void UpdateMarketNameTest() {
        marketDao.updateMarketName("Updated Name", 1);

        assertEquals("Updated Name", marketDao.getMarketById(1).getName());
    }


    @Test
    public void UpdateMarketDescriptionTest() {
        marketDao.updateMarketDescription("Updated Description", 1);

        assertEquals("Updated Description", marketDao.getMarketById(1).getDescription());
    }

    @Test
    public void GetMarketByIdTest() {

        Market result = marketDao.getMarketById(1);

        assertEquals("Market1", result.getName());
        assertEquals(1, result.getMarketID());
        assertEquals("for someone  somewhere anywhere at anytime!", result.getDescription());
    }

    @Test
    public void GetMarketByIdNotFoundTest() {
        assertThrows(MarketNotFoundException.class, ()->marketDao.getMarketById(5));
    }

    @Test
    public void DeleteMarketTest() {

        assertNotNull(marketDao.getMarketById(3));

        marketDao.deleteMarket(3);

        assertThrows(MarketNotFoundException.class, ()->marketDao.getMarketById(3));

    }




}
