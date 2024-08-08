package com.local.market.my_local_market.service;

import com.local.market.my_local_market.model.Product;
import com.local.market.my_local_market.model.Stand;
import com.local.market.my_local_market.repository.StandDao;
import com.local.market.my_local_market.util.StandUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StandService {

    private final StandDao standRepository;
    private final StandUtil standUtil;

    @Autowired
    public StandService(StandDao standRepository, StandUtil standUtil) {
        this.standRepository = standRepository;
        this.standUtil = standUtil;
    }


    public void registerStand(Stand stand) {
        standRepository.createStand(stand.getProviderID(),stand.getMarketID(), stand.getName());
    }


    public Stand getStandById(Integer id) {
        return standRepository.getStandById(id);
    }


    public List<Stand> getAllStands() {
        return standRepository.getAllStands();
    }

    public void deleteStand(Integer id) {
        standRepository.deleteStand(id);
    }


    public List<Product> getStock(Integer id) {
        return standRepository.getStock(id);
    }

    public List<Stand> getAllStockbyID(Integer providerID) { return standRepository.getAllStandsbyProviderID(providerID);
    }
}
