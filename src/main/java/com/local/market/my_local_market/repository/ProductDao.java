package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.ProductNotFoundException;
import com.local.market.my_local_market.exceptions.ProductNotFoundException;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
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
        return jdbcTemplate.query("SELECT * FROM PRODUCTS", new ProductRowMapper());
    }

    public int createProduct(Integer provID, Float price, Integer amount, Integer standID, String name) {
        return jdbcTemplate.update("INSERT INTO PRODUCTS(PROVIDERID, PRICE, AMOUNT, STANDID, NAME) VALUES ( ?, ?, ?, ?, ?)", provID, price, amount, standID, name);
    }

    public int updateProduct(Integer provID, Float price, Integer amount, Integer standID, String name, Integer prodID) {
        return jdbcTemplate.update("UPDATE PRODUCTS SET PROVIDERID = ?, PRICE = ?, AMOUNT = ?, STANDID = ?, NAME = ?  WHERE PRODUCTID = ?",  provID, price, amount, standID, name, prodID);
    }
    public int updateProductName(String name, Integer id) {
        return jdbcTemplate.update("UPDATE PRODUCTS SET NAME = ? WHERE PRODUCTID = ?",  name, id);
    }

    public int updateProductPrice(Integer price, Integer id) {
        return jdbcTemplate.update("UPDATE PRODUCTS SET PRICE = ? WHERE PRODUCTID = ?",  price, id);
    }
    public int updateProductAmount(Integer amount, Integer id) {
        return jdbcTemplate.update("UPDATE AMOUNT SET PRICE = ? WHERE PRODUCTID = ?",  amount, id);
    }
    public int deleteProduct(int id) {
        return jdbcTemplate.update("DELETE FROM PRODUCTS WHERE PRODUCTID = ?", id);
    }

    public Product getProductById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PRODUCTS WHERE PRODUCTID = ?", new ProductRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ProductNotFoundException(String.format("Product with id %s was not found", id));
        }
    }
}
