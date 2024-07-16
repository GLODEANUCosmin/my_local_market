package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.UserNotFoundException;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.mappers.UserRowMapper;
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
        return jdbcTemplate.query("SELECT * FROM USERS", new UserRowMapper());
    }

    public int createUser(String name, Integer sum, String password) {
        return jdbcTemplate.update("INSERT INTO USERS(NAME, WALLET, PASSWORD) VALUES ( ?, ?, ?)", name, sum, password);
    }

    public int updateUser(String name, String password, Integer money, Integer id) {
        return jdbcTemplate.update("UPDATE USERS SET NAME = ?, WALLET = ?, PASSWORD = ? WHERE USERID = ?",  name, money, password, id);
    }

    public int updateUserName(String name, Integer id) {
        return jdbcTemplate.update("UPDATE USERS SET NAME = ? WHERE USERID = ?",  name, id);
    }

    public int updateUserWallet(Integer money, Integer id) {
        return jdbcTemplate.update("UPDATE USERS SET Wallet = ? WHERE USERID = ?",  money, id);
    }

    public int updateUserPassword(String password, Integer id) {
        return jdbcTemplate.update("UPDATE USERS SET PASSWORD = ? WHERE USERID = ?", password, id);
    }

    public int deleteUser(int id) {
        return jdbcTemplate.update("DELETE FROM USERS WHERE USERID = ?", id);
    }

    public User getUserById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE USERID = ?", new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(String.format("User with id %s was not found", id));
        }
    }

}