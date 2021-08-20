package com.playtomic.tests.wallet.repository;

import com.playtomic.tests.wallet.model.Wallet;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "wallets", path = "wallets")
public interface WalletRepository extends PagingAndSortingRepository<Wallet, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
    
}
