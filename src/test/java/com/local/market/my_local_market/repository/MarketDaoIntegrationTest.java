package com.local.market.my_local_market.repository;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarketDaoIntegrationTest {

    @Test
    public void getAllMarketsTest() {

        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)

                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        MarketDao marketDao = new MarketDao();
        marketDao.setDataSource(dataSource);

        assertEquals(10, marketDao.getAllMarkets());

    }
}
