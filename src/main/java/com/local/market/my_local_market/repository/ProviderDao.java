package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.ProviderNotFoundException;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Provider;
import com.local.market.my_local_market.model.Stand;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import com.local.market.my_local_market.repository.mappers.ProviderRowMapper;
import com.local.market.my_local_market.repository.mappers.StandRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class ProviderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Provider> getAllProviders() {
        return jdbcTemplate.query("SELECT * FROM \"Providers\" p LEFT JOIN \"Users\" u ON p.\"UserID\" = u.\"UserID\"", new ProviderRowMapper());
    }

    public int createProvider(Integer id, String codCUI) {

        return jdbcTemplate.update("INSERT INTO \"Providers\"(\"UserID\", \"CodCUI\") VALUES ( ?, ?)", id, codCUI);
    }

    public int updateCodCUI(String codCUI, Integer id) {
        return jdbcTemplate.update("UPDATE \"Providers\" SET \"CodCUI\" = ? WHERE \"UserID\" = ?", codCUI, id);
    }

    public int updateRating(Integer rating, Integer id) {
        System.out.println("rating: " + rating);
        return jdbcTemplate.update("UPDATE \"Providers\" SET \"Rating\" = ? WHERE \"UserID\" = ?", rating, id);
    }

    public int deleteProvider(int id) {
        return jdbcTemplate.update("DELETE FROM \"Providers\" WHERE \"UserID\" = ?", id);
    }

    public Provider getProviderById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"Providers\" p LEFT JOIN \"Users\" u ON p.\"UserID\" = u.\"UserID\" WHERE p.\"UserID\" = ?", new ProviderRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ProviderNotFoundException(String.format("Provider with id %s was not found", id));
        }
    }

    public List<Stand> getAllTheirStands(Integer id) {
        return jdbcTemplate.query("SELECT * FROM \"Stands\" WHERE \"ProviderID\" = ?", new StandRowMapper(), id);
    }

    public List<Product> getAllTheirStock(Integer id) {
        return jdbcTemplate.query("SELECT * FROM \"Products\" WHERE \"ProviderID\" = ?", new ProductRowMapper(), id);
    }

    public int updateProvider(String codCUI, Integer rating, Integer id) {
        return jdbcTemplate.update("UPDATE \"Providers\" SET \"CodCUI\" = ?, \"Rating\" = ? WHERE \"UserID\" = ?",  codCUI, rating, id);
    }
}
