package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.ProductNotFoundException;
import com.local.market.my_local_market.exceptions.ProductNotFoundException;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Type;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import com.local.market.my_local_market.repository.mappers.StandRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class ProductDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("SELECT * FROM \"Products\"", new ProductRowMapper());
    }

    public int createProduct(Integer provID, Float price, Integer amount, Integer standID, String name, Type tip) {
        return jdbcTemplate.update("INSERT INTO \"Products\"(\"ProviderID\", \"Price\", \"Amount\", \"StandID\", \"Name\", \"Type\") VALUES ( ?, ?, ?, ?, ?, ?)", provID, price, amount, standID, name, Type.typeToString(tip));
    }

    public int updateProduct(Integer provID, Float price, Integer amount, Integer standID, String name, Integer prodID) {
        return jdbcTemplate.update("UPDATE \"Products\" SET \"ProviderID\" = ?, \"Price\" = ?, \"Amount\" = ?, \"StandID\" = ?, \"Name\" = ?  WHERE \"ProductID\" = ?",  provID, price, amount, standID, name, prodID);
    }
    public int updateProductName(String name, Integer id) {
        return jdbcTemplate.update("UPDATE \"Products\" SET \"Name\" = ? WHERE \"ProductID\" = ?",  name, id);
    }

    public int updateProductPrice(Float price, Integer id) {
        return jdbcTemplate.update("UPDATE \"Products\" SET \"Price\" = ? WHERE \"ProductID\" = ?",  price, id);
    }
    public int updateProductAmount(Integer amount, Integer id) {
        return jdbcTemplate.update("UPDATE \"Products\" SET \"Amount\" = ? WHERE \"ProductID\" = ?",  amount, id);
    }
    public int updateProductType(Type tip, Integer id) {
        return jdbcTemplate.update("UPDATE \"Products\" SET \"Type\" = ? WHERE \"ProductID\" = ?",  Type.typeToString(tip), id);
    }
    public int deleteProduct(int id) {
        return jdbcTemplate.update("DELETE FROM \"Products\" WHERE \"ProductID\" = ?", id);
    }

    public Product getProductById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"Products\" WHERE \"ProductID\" = ?", new ProductRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ProductNotFoundException(String.format("Product with id %s was not found", id));
        }
    }

    public List<Product> getProductsByStandID(Integer standID){

        return jdbcTemplate.query("SELECT * FROM \"Products\" WHERE \"StandID\" = ?", new ProductRowMapper(), standID);
    }
    public List<Product> getProductsByProviderID(Integer providerID){
        return jdbcTemplate.query("SELECT * FROM \"Products\" WHERE \"ProviderID\" = ?", new ProductRowMapper(), providerID);
    }
}
