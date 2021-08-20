package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.dto.mapper.WalletMapper;
import com.playtomic.tests.wallet.dto.model.WalletDTO;
import com.playtomic.tests.wallet.dto.request.ChargeRequest;
import com.playtomic.tests.wallet.dto.request.RechargeRequest;
import com.playtomic.tests.wallet.service.WalletService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    private final WalletMapper walletMapper;

    @PatchMapping("/wallets/{id}/recharges")
    public ResponseEntity<WalletDTO> rechargeWallet(@PathVariable Long id,
            @RequestBody RechargeRequest rechargeRequest) {
        log.debug("Recharge Wallet {}", id);
        return ResponseEntity.ok(walletService.rechargeWallet(walletMapper.toWalletDTO(id),
                rechargeRequest.getCardPan(), rechargeRequest.getAmount()));
    }

    @PatchMapping("/wallets/{id}/charges")
    public ResponseEntity<WalletDTO> subtractBalance(@PathVariable Long id, @RequestBody ChargeRequest chargeRequest) {
        log.debug("Subtract balance on Wallet {}", id);
        return ResponseEntity
                .ok(walletService.subtractBalance(walletMapper.toWalletDTO(id), chargeRequest.getAmount()));
    }

}
