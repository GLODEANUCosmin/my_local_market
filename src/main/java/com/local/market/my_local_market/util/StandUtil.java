package com.local.market.my_local_market.util;

import com.local.market.my_local_market.model.Stand;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StandUtil {

    public void patchStand(Stand stand, Map<String, String> partialStand) {
        String marketID = partialStand.get("MarketID");
        String providerID = partialStand.get("ProviderID");
        if (!StringUtils.isNullOrEmpty(providerID)) {
            stand.setProviderID(Integer.parseInt(providerID));
        }
        if (!StringUtils.isNullOrEmpty(marketID)) {
            stand.setMarketID(Integer.parseInt(marketID));
        }
    }

}