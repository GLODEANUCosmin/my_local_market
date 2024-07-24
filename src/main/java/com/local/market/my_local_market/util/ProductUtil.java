package com.local.market.my_local_market.util;

import com.local.market.my_local_market.model.Product;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;



@Component
public class ProductUtil {
    public void patchProduct(Product product, Map<String, String> partialProduct) {
        String name = partialProduct.get("name");
        String providerID = partialProduct.get("providerID");
        String standID = partialProduct.get("standID");
        String price = partialProduct.get("price");
        String amount = partialProduct.get("amount");
        if (!StringUtils.isNullOrEmpty(name)) {
            product.setName(name);
        }
        if (!StringUtils.isNullOrEmpty(providerID)) {
            product.setProviderID(Integer.parseInt(providerID));
        }
        if (!StringUtils.isNullOrEmpty(standID)) {
            product.setStandID(Integer.parseInt(standID));
        }
        if (!StringUtils.isNullOrEmpty(price)) {
            product.setPrice(Integer.parseInt(price));
        }
        if (!StringUtils.isNullOrEmpty(amount)) {
            product.setAmount(Integer.parseInt(amount));
        }

    }
}
