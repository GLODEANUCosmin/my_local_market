package com.local.market.my_local_market.service;
import com.local.market.my_local_market.model.Market;
import com.local.market.my_local_market.model.Stand;
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
        marketRepository.createMarket(market.getName(), market.getDescription());
    }


    public Market getMarketById(Integer id) {
        return marketRepository.getMarketById(id);
    }


    public List<Market> getAllMarkets() {
        return marketRepository.getAllMarkets();
    }

    public List<Stand> getAllStands(Integer marketID) {
        return marketRepository.getAllStands(marketID);
    }

    public void updateMarket(Integer id, Market market) {
        marketRepository.updateMarketName(market.getName(),id);
    }


    public void patchMarket(Integer id, Map<String, String> partialMarket) {
        Market market = new Market();

        marketUtil.patchMarket(market, partialMarket);
        if(market.getName()!=null){marketRepository.updateMarketName(market.getName(),id);}
        if(market.getDescription()!=null){marketRepository.updateMarketDescription(market.getDescription(),id);}
    }


    public void deleteMarket(Integer id) {
        marketRepository.deleteMarket(id);
    }
}
