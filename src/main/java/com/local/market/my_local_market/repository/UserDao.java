package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.UserNotFoundException;
import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Stand;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.mappers.ProductRowMapper;
import com.local.market.my_local_market.repository.mappers.StandRowMapper;
import com.local.market.my_local_market.repository.mappers.UserRowMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM \"Users\"", new UserRowMapper());
    }

    public int createUser(String name, Float sum, String password) {
        return jdbcTemplate.update("INSERT INTO \"Users\" (\"Name\", \"Wallet\", \"Password\") VALUES ( ?, ?, ?)", name, sum, password);
    }

    public int updateUser(String name, String password, Float money, Integer id) {
        return jdbcTemplate.update("UPDATE \"Users\" SET \"Name\" = ?, \"Wallet\" = ?, \"Password\" = ? WHERE \"UserID\" = ?",  name, money, password, id);
    }

    public int updateUserName(String name, Integer id) {
        return jdbcTemplate.update("UPDATE \"Users\" SET \"Name\" = ? WHERE \"UserID\" = ?",  name, id);
    }

    public int updateUserWallet(Float money, Integer id) {
        return jdbcTemplate.update("UPDATE \"Users\" SET \"Wallet\" = ? WHERE \"UserID\" = ?",  money, id);

    }

    public int updateUserPassword(String password, Integer id) {
        return jdbcTemplate.update("UPDATE \"Users\" SET \"Password\" = ? WHERE \"UserID\" = ?", password, id);
    }

    public int deleteUser(int id) {
        return jdbcTemplate.update("DELETE FROM \"Users\" WHERE \"UserID\" = ?", id);
    }

    public User getUserById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"Users\" WHERE \"UserID\" = ?", new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(String.format("User with id %s was not found", id));
        }
    }









}