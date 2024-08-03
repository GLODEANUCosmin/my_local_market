package com.local.market.my_local_market.controller;


import com.local.market.my_local_market.model.Provider;
import com.local.market.my_local_market.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping(value = "/providers/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerProvider(@RequestBody Provider provider) {
        providerService.registerProvider(provider);
    }

    @GetMapping(value = "/providers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Provider getProviderById(@PathVariable Integer id) {
        return providerService.getProviderById(id);
    }

    @GetMapping(value = "/providers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }


    @PutMapping(value = "/providers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateProvider(@PathVariable Integer id, @RequestBody Provider provider) {
        System.out.println("ProvCont");
        providerService.updateProvider(id, provider);
    }

    @PatchMapping(value = "/providers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void patchProvider(@PathVariable Integer id, @RequestBody Map<String, String> partialProvider) {
        providerService.patchProvider(id, partialProvider);
    }

    @DeleteMapping(value = "/providers/{id}")
    public void deleteProvider(@PathVariable Integer id) {
        providerService.deleteProvider(id);
    }


}
