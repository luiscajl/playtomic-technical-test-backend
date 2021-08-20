package com.playtomic.tests.wallet.service;

import java.math.BigDecimal;

import com.playtomic.tests.wallet.dto.mapper.WalletMapper;
import com.playtomic.tests.wallet.dto.model.WalletDTO;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private StripeService stripeService;

    @Autowired
    private WalletMapper walletMapper;

    /**
     * - La parte de la concurrencia no está bien definida en el README. ¿Se
     *   requiere en la parte de la recarga del monedero? 
     * - Falta checkear si se queda en saldo negativo 
     * - Añadido testing con github actions 
     * - Spring data rest está en el pom. Para operativas complicadas se debe hacer override de los
     *    metodos y realizar su propia implementación. 
     * - Añadido lombok
     */

    @Override
    public WalletDTO rechargeWallet(WalletDTO walletDTO, String cardPan, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletDTO.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found wallet with this wallet id"));
        synchronized (this) {
            try {
                stripeService.charge(cardPan, amount);
            } catch (StripeServiceException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to charge amount on the wallet");
            }
        }
        BigDecimal newBalance = wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);
        return walletMapper.toWalletDTO(walletRepository.save(wallet));
    }

    @Override
    public WalletDTO subtractBalance(WalletDTO walletDTO, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletDTO.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found wallet with this wallet id"));
        BigDecimal newBalance = wallet.getBalance().subtract(amount);
        wallet.setBalance(newBalance);
        return walletMapper.toWalletDTO(walletRepository.save(wallet));
    }

}
