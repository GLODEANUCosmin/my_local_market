package com.local.market.my_local_market.repository.mappers;

import com.local.market.my_local_market.model.Stand;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StandRowMapper implements RowMapper<Stand> {
    @Override
    public Stand mapRow(final ResultSet rs, final int rowNum) throws SQLException{
        final Stand stand = new Stand();

        stand.setMarketID(rs.getInt("MarketID"));
        stand.setStandID(rs.getInt("StandID"));
        stand.setProviderID(rs.getInt("ProviderID"));

        return stand;
    }
}
