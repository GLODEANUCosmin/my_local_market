package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.MarketNotFoundException;
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
        return jdbcTemplate.query("SELECT * FROM \"Stands\" ", new StandRowMapper());
    }

    public int createStand(Integer providerID, Integer marketID, String name) {
        return jdbcTemplate.update("INSERT INTO \"Stands\"(\"ProviderID\", \"MarketID\", \"Name\") VALUES ( ?, ?, ?)", providerID, marketID, name);


    }

    public Stand getStandById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"Stands\" WHERE \"StandID\" = ?", new StandRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new StandNotFoundException(String.format("Stand with id %s was not found", id));
        }
    }

    public int deleteStand(int id) {
        return jdbcTemplate.update("DELETE FROM \"Stands\" WHERE \"StandID\" = ?", id);
    }


    public List<Product> getStock(Integer standid) {
        return jdbcTemplate.query("SELECT * FROM \"Products\" WHERE \"StandID\" = ?", new ProductRowMapper(), standid);
    }

    public Market getMarketById(Integer marketid) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"Markets\" WHERE \"MarketID\" = ?", new MarketRowMapper(), marketid);
        } catch (EmptyResultDataAccessException ex) {
            throw new MarketNotFoundException(String.format("Market with id %s was not found", marketid));
        }
    }


    public List<Stand> getAllStockbyID(Integer providerID) {
        return jdbcTemplate.query("SELECT * FROM \"Stands\" WHERE \"ProviderID\" = ?", new StandRowMapper(), providerID);
    }
}
