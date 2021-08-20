package com.playtomic.tests.wallet.service;

import java.math.BigDecimal;

import com.playtomic.tests.wallet.dto.model.WalletDTO;

public interface WalletService {

    WalletDTO rechargeWallet(WalletDTO walletDTO, String cardPan, BigDecimal amount);

    WalletDTO subtractBalance(WalletDTO walletDTO, BigDecimal amount);

}
