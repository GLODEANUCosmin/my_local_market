package com.local.market.my_local_market.util;

import com.local.market.my_local_market.model.Provider;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;



@Component
public class ProviderUtil {
    public void patchProvider(Provider provider, Map<String, String> partialProvider) {
        String name = partialProvider.get("name");
        String wallet = partialProvider.get("wallet");
        String password = partialProvider.get("password");
        String codCUI = partialProvider.get("codCUI");
        String rating = partialProvider.get("rating");
        if (!StringUtils.isNullOrEmpty(name)) {
            provider.setName(name);
        }
        if (!StringUtils.isNullOrEmpty(wallet)) {
            provider.setWallet(Float.parseFloat(wallet));
        }
        if (!StringUtils.isNullOrEmpty(password)) {
            provider.setPassword(password);
        }
        if (!StringUtils.isNullOrEmpty(codCUI)) {
            provider.setCodCui(codCUI);
        }
        if (!StringUtils.isNullOrEmpty(rating)) {
            provider.setWallet(Float.parseFloat(rating));
        }

    }

}