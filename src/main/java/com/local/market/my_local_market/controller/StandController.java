package com.local.market.my_local_market.controller;

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
    public void registerStand(@RequestBody Stand stand) {
        standService.registerStand(stand);
    }

    @GetMapping(value = "markets/{marketID}/stands/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public Stand getStandById(@PathVariable Integer id) {
        return standService.getStandById(id);
    }

    @GetMapping(value = "markets/{marketID}/stands", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stand> getAllStands(@PathVariable int marketID) {
        return marketService.getAllStands(marketID);
    }


    @DeleteMapping(value = "markets/{marketID}/stands/{id}")
    public void deleteStand(@PathVariable Integer id) {
        standService.deleteStand(id);
    }
}
