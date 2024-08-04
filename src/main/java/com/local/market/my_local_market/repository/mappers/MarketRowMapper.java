package com.local.market.my_local_market.repository.mappers;

import com.local.market.my_local_market.model.Market;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketRowMapper implements RowMapper<Market> {
    @Override
    public Market mapRow(final ResultSet rs, final int rowNum) throws SQLException{
        final Market market = new Market();

        market.setMarketID(rs.getInt("MarketID"));
        market.setName(rs.getString("Name"));

        return market;
    }
}
