package com.interview.purchase_api.service;

import com.interview.purchase_api.dto.UserPurchaseDTO;
import com.interview.purchase_api.mapper.UserPurchaseMapper;
import com.interview.purchase_api.repository.BuyUserRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyUserService {

    private final BuyUserRepository repository;

    public BuyUserService(BuyUserRepository repository) {
        this.repository = repository;
    }

    public void save(UserPurchaseDTO compraUsuario) {
        repository.save(UserPurchaseMapper.INSTANCE.mapToBuyUser(compraUsuario));
    }
}
