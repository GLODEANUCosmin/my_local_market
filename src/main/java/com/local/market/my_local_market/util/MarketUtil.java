package com.local.market.my_local_market.util;

import com.local.market.my_local_market.model.Market;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MarketUtil {
    public void patchMarket(Market market, Map<String, String> partialMarket) {
        String name = partialMarket.get("name");
        String description = partialMarket.get("description");
        if (!StringUtils.isNullOrEmpty(name)) {
            market.setName(name);
        }
        if (!StringUtils.isNullOrEmpty(description)) {
            market.setDescription(description);
        }


    }
}
