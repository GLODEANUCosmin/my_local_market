package com.local.market.my_local_market.repository.mappers;


import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Type;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(final ResultSet rs, final int rowNum) throws SQLException{
        final Product product = new Product();

        product.setProductID(rs.getInt("ProductID"));
        product.setStandID(rs.getInt("StandID"));
        product.setProviderID(rs.getInt("ProviderID"));
        product.setAmount(rs.getInt("Amount"));
        product.setPrice(rs.getFloat("Price"));
        product.setName(rs.getString("Name"));
        product.setTip(Type.stringToType(rs.getString("Type")));

        return product;
    }
}
