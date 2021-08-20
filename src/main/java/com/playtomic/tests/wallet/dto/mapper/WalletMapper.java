package com.playtomic.tests.wallet.dto.mapper;

import com.playtomic.tests.wallet.dto.model.WalletDTO;
import com.playtomic.tests.wallet.model.Wallet;

import org.springframework.stereotype.Component;


@Component
public class WalletMapper {

    public  WalletDTO toWalletDTO(Wallet wallet) {
        return WalletDTO.builder().balance(wallet.getBalance()).id(wallet.getId()).build();
    }

    public  WalletDTO toWalletDTO(Long id) {
        return WalletDTO.builder().id(id).build();
    }
}
