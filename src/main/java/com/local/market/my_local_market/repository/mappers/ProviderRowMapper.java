package com.local.market.my_local_market.repository.mappers;

import com.local.market.my_local_market.model.Provider;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderRowMapper implements RowMapper<Provider> {

    @Override
    public Provider mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Provider provider = new Provider();

        provider.setId(rs.getInt("UserID"));
        provider.setName(rs.getString("Name"));
        provider.setWallet(rs.getFloat("Wallet"));
        provider.setPassword(rs.getString("Password"));
        provider.setCodCUI(rs.getString("CodCUI"));
        provider.setRating(rs.getInt("Rating"));

        return provider;
    }
}