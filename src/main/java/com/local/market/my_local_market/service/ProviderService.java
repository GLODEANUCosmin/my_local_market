package com.local.market.my_local_market.service;
import com.local.market.my_local_market.model.Provider;
import com.local.market.my_local_market.repository.ProviderDao;
import com.local.market.my_local_market.repository.UserDao;
import com.local.market.my_local_market.util.ProviderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ProviderService {
    private final UserDao userRepository;
    private final ProviderDao providerRepository;
    private final ProviderUtil providerUtil;

    @Autowired
    public ProviderService(UserDao userRepository, ProviderDao providerRepository, ProviderUtil providerUtil) {
        this.userRepository=userRepository;
        this.providerRepository = providerRepository;
        this.providerUtil = providerUtil;
    }


    public void registerProvider(Provider provider) {
        providerRepository.createProvider(provider.getId(),provider.getCodCui());
    }


    public Provider getProviderById(Integer id) {
        return providerRepository.getProviderById(id);
    }


    public List<Provider> getAllProviders() {
        return providerRepository.getAllProviders();
    }


    public void updateProvider(Integer id, Provider provider) {
        providerRepository.updateCodCUI(provider.getCodCui(), id);
    }


    public void patchProvider(Integer id, Map<String, String> partialProvider) {
        Provider provider = providerRepository.getProviderById(id);

        providerUtil.patchProvider(provider, partialProvider);

        providerRepository.updateCodCUI(provider.getCodCui(), id);
        userRepository.updateUser(provider.getName(),provider.getPassword(),provider.getWallet(),id);
    }


    public void deleteProvider(Integer id) {
        providerRepository.deleteProvider(id);
    }
}
