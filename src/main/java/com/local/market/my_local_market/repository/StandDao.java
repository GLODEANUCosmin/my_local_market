package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.StandNotFoundException;
import com.local.market.my_local_market.model.Market;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Stand;
import com.local.market.my_local_market.repository.mappers.MarketRowMapper;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import com.local.market.my_local_market.repository.mappers.StandRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class StandDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Stand> getAllStands() {
        return jdbcTemplate.query("SELECT * FROM STANDS ", new StandRowMapper());
    }

    public int createStand(Integer providerID, Integer marketID) {
        return jdbcTemplate.update("INSERT INTO STANDS(PROVIDERID, MARKETID) VALUES ( ?, ?)", providerID, marketID);


    }

    public Stand getStandById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM STANDS WHERE STANDID = ?", new StandRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new StandNotFoundException(String.format("Stand with id %s was not found", id));
        }
    }

    public int deleteStand(int id) {
        return jdbcTemplate.update("DELETE FROM STANDS WHERE STANDID = ?", id);
    }


    public List<Product> getStock(Integer id) {
        return jdbcTemplate.query("SELECT * FROM PRODUCTS WHERE STANDID = ?", new ProductRowMapper(), id);
    }

    public Market getMarketById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MARKETS WHERE MARKETID = ?", new MarketRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new StandNotFoundException(String.format("Stand with id %s was not found", id));
        }
    }

}
