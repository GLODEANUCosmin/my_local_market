package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.MarketNotFoundException;
import com.local.market.my_local_market.exceptions.StandNotFoundException;
import com.local.market.my_local_market.model.Market;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Stand;
import com.local.market.my_local_market.model.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.DirtiesContext;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StandDaoIntegrationTest {

    DataSource dataSource;

    StandDao standDao;

    @BeforeEach
    public void setUp(){
        //
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("reset.sql")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        standDao = new StandDao();
        standDao.setDataSource(dataSource);
    }

    @Test
    void getAllStandsTest() {
        List<Stand> stands = standDao.getAllStands();

        assertNotNull(stands);
        assertEquals(10, stands.size());
        assertEquals(1, stands.get(0).getStandID());
        assertEquals("apples@co", stands.get(0).getName());
        assertEquals(3, stands.get(2).getMarketID());
        assertEquals(2, stands.get(4).getProviderID());
    }

    @Test
    void createStandTest() {
        standDao.createStand(1, 1, "stand");

        Stand stand = new Stand();
        stand=standDao.getStandById(11);

        assertEquals(1, stand.getProviderID());
        assertEquals(1,stand.getMarketID());
        assertEquals("stand", stand.getName());
    }

    @Test
    void getStandByIdTest() {
        Stand stand = new Stand();
        stand=standDao.getStandById(4);

        assertNotNull(stand);
        assertEquals(2, stand.getProviderID());
        assertEquals(1, stand.getMarketID());
        assertEquals("pears", stand.getName());
    }

    @Test
    void getStandByIdFailedTest() {
        assertThrows(StandNotFoundException.class, () -> standDao.getStandById(11));
    }

    @Test
    void deleteStandTest() {
        Stand stand = new Stand();
        stand=standDao.getStandById(4);

        standDao.deleteStand(4);

        assertThrows(StandNotFoundException.class,()->standDao.getStandById(4));
    }

    @Test
    void getStockTest() {
        List<Product> result = standDao.getStock(2);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(4, result.get(0).getProductID());
        assertEquals(1, result.get(0).getProviderID());
        assertEquals(5f, result.get(0).getPrice());
        assertEquals(100, result.get(0).getAmount());
        assertEquals("Candy Apples", result.get(0).getName());
        assertEquals(Type.Cores, result.get(0).getTip());
    }

    @Test
    void getMarketByIdTest() {
        Market result = standDao.getMarketById(1);

        assertNotNull(result);
        assertEquals("Market1", result.getName());
    }

    @Test
    void getMarketByIdFailedTest(){
        assertThrows(MarketNotFoundException.class, () -> standDao.getMarketById(11));
    }

    @Test
    void getAllStandsByProviderIDTest() {
        List<Stand> stands = standDao.getAllStandsbyProviderID(1);

        assertNotNull(stands);
        assertEquals(3, stands.size());
        assertEquals(1, stands.get(0).getStandID());
        assertEquals(1, stands.get(0).getProviderID());
        assertEquals(1, stands.get(0).getMarketID());
        assertEquals("apples@co", stands.get(0).getName());
    }

}
