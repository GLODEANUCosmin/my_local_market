package com.local.market.my_local_market.controller;

import com.local.market.my_local_market.model.Market;
import com.local.market.my_local_market.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
public class MarketController {
    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping(value = "/markets", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerMarket(@RequestBody Market market) {
        marketService.registerMarket(market);
    }

    @GetMapping(value = "/markets/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public Market getMarketById(@PathVariable Integer id) {
        return marketService.getMarketById(id);
    }

    @GetMapping(value = "/markets", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Market> getAllMarkets() {
        return marketService.getAllMarkets();
    }

    @PutMapping(value = "/markets/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMarket(@PathVariable Integer id, @RequestBody Market market) {
        marketService.updateMarket(id, market);
    }

    @PatchMapping(value = "/markets/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void patchMarket(@PathVariable Integer id, @RequestBody Map<String, String> partialMarket) {
        marketService.patchMarket(id, partialMarket);
    }

    @DeleteMapping(value = "/markets/{id}")
    public void deleteMarket(@PathVariable Integer id) {
        marketService.deleteMarket(id);
    }

}
