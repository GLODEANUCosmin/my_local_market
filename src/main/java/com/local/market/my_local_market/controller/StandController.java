package com.local.market.my_local_market.controller;

import com.local.market.my_local_market.model.Provider;
import com.local.market.my_local_market.model.Stand;
import com.local.market.my_local_market.service.MarketService;
import com.local.market.my_local_market.service.StandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
public class StandController {

    private final StandService standService;
    private final MarketService marketService;

    @Autowired
    public StandController(StandService standService, MarketService marketService) {
        this.standService = standService;
        this.marketService = marketService;
    }

    @PostMapping(value = "markets/{marketID}/stands", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerStand(@PathVariable Integer marketID, @RequestBody Stand stand) {
        stand.setMarketID(marketID);
        standService.registerStand(stand);
    }

    @GetMapping(value = "markets/{marketID}/stands/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Stand getStandByIdMarket(@PathVariable Integer id) {
        return standService.getStandById(id);
    }

    @GetMapping(value = "markets/{marketID}/stands", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stand> getAllStandsOfMarket(@PathVariable int marketID) {
        return marketService.getAllStands(marketID);
    }


    @DeleteMapping(value = "markets/{marketID}/stands/{id}")
    public void deleteStand(@PathVariable Integer id) {
        standService.deleteStand(id);
    }


    @GetMapping(value = "/providers/{providerID}/stands", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stand> getAllStandsOfProvider(@PathVariable Integer providerID) {
        return standService.getAllStockbyID(providerID);
    }

    @GetMapping(value = "/providers/{providerID}/stands/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Stand getStandByIdProvider(@PathVariable Integer id) {
        return standService.getStandById(id);
    }



}
