package com.local.market.my_local_market.util;

import com.local.market.my_local_market.model.User;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserUtil {

    public void patchUser(User user, Map<String, String> partialUser) {
        String Name = partialUser.get("Name");
        String Wallet = partialUser.get("Wallet");
        String password = partialUser.get("password");
        if (!StringUtils.isNullOrEmpty(Name)) {
            user.setName(Name);
        }
        if (!StringUtils.isNullOrEmpty(Wallet)) {
            user.setWallet(Float.parseFloat(Wallet));
        }
        if (!StringUtils.isNullOrEmpty(password)) {
            user.setPassword(password);
        }

    }

}