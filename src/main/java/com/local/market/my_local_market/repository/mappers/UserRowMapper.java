package com.local.market.my_local_market.repository.mappers;

import com.local.market.my_local_market.model.User;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final User user = new User();

        user.setId(rs.getInt("UserID"));
        user.setName(rs.getString("Name"));
        user.setWallet(rs.getFloat("Wallet"));
        user.setPassword(rs.getString("password"));

        return user;
    }
}
