package com.local.market.my_local_market.service;
import com.local.market.my_local_market.model.Market;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.MarketDao;
import com.local.market.my_local_market.util.MarketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MarketService {
    private final MarketDao marketRepository;
    private final MarketUtil marketUtil;

    @Autowired
    public MarketService(MarketDao marketRepository, MarketUtil marketUtil) {
        this.marketRepository = marketRepository;
        this.marketUtil = marketUtil;
    }

    public void registerMarket(Market market) {
        marketRepository.createMarket(market.getName());
    }


    public Market getMarketById(Integer id) {
        return marketRepository.getMarketById(id);
    }


    public List<Market> getAllMarkets() {
        return marketRepository.getAllMarkets();
    }


    public void updateMarket(Integer id, Market market) {
        marketRepository.updateMarketName(market.getName(),id);
    }


    public void patchMarket(Integer id, Map<String, String> partialMarket) {
        Market market = marketRepository.getMarketById(id);

        marketUtil.patchMarket(market, partialMarket);

        marketRepository.updateMarketName(market.getName(), id);
    }


    public void deleteMarket(Integer id) {
        marketRepository.deleteMarket(id);
    }
}
