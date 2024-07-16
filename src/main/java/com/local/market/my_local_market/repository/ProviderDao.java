package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.ProviderNotFoundException;
import com.local.market.my_local_market.model.Provider;
import com.local.market.my_local_market.repository.mappers.ProviderRowMapper;
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
        return jdbcTemplate.query("SELECT * FROM PROVIDERS", new ProviderRowMapper());
    }

    public int createProvider(Integer id, String codCUI) {
        return jdbcTemplate.update("INSERT INTO PROVIDERS(USERID, CODCUI) VALUES ( ?, ?)", id, codCUI);
    }

    public int updateCodCUI(String codCUI, Integer id) {
        return jdbcTemplate.update("UPDATE PROVIDERS SET CODCUI = ? WHERE USERID = ?",codCUI , id);
    }

    public int deleteProvider(int id) {
        return jdbcTemplate.update("DELETE FROM PROVIDERS WHERE USERID = ?", id);
    }

    public Provider getProviderById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PROVIDERS WHERE USERID = ?", new ProviderRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ProviderNotFoundException(String.format("Provider with id %s was not found", id));
        }
    }
}
