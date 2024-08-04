package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.MarketNotFoundException;
import com.local.market.my_local_market.model.Market;
import com.local.market.my_local_market.model.Stand;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.mappers.MarketRowMapper;
import com.local.market.my_local_market.repository.mappers.StandRowMapper;
import com.local.market.my_local_market.repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MarketDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Market> getAllMarkets() {
        return jdbcTemplate.query("SELECT * FROM \"Markets\"", new MarketRowMapper());
    }

    public int createMarket(String name, String description) {
        return jdbcTemplate.update("INSERT INTO \"Markets\" (\"Name\" , \"Description\") VALUES (?, ?)", name, description);
    }

    public int updateMarketName(String name, Integer id) {
        return jdbcTemplate.update("UPDATE \"Markets\" SET \"Name\" = ? WHERE \"MarketID\" = ?",  name, id);
    }

    public int updateMarketDescription(String description, Integer id) {
        return jdbcTemplate.update("UPDATE \"Markets\" SET \"Description\" = ? WHERE \"MarketID\" = ?",  description, id);
    }

    public Market getMarketById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"Markets\" WHERE \"MarketID\" = ?", new MarketRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new MarketNotFoundException(String.format("Market with id %s was not found", id));
        }
    }

    public List<Stand> getAllStands(Integer id) {
        return jdbcTemplate.query("SELECT * FROM \"Stands\" WHERE \"MarketID\" = ?", new StandRowMapper(), id);
    }

    public int deleteMarket(int id) {
        return jdbcTemplate.update("DELETE FROM \"Markets\" WHERE \"MarketID\" = ?", id);
    }

}
