package com.playtomic.tests.wallet.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.playtomic.tests.wallet.dto.mapper.WalletMapper;
import com.playtomic.tests.wallet.dto.model.WalletDTO;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.StripeService;
import com.playtomic.tests.wallet.service.WalletService;
import com.playtomic.tests.wallet.service.WalletServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @InjectMocks
    private WalletService walletService = new WalletServiceImpl();

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private StripeService stripeService;

    @Mock
    private WalletMapper walletMapper;

    @Test
    public void testSubstractOk() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal(100));
        wallet.setId(new Long("1"));
        WalletDTO resultMapper = WalletDTO.builder().balance(new BigDecimal(90)).build();
        when(walletRepository.findById(new Long("1"))).thenReturn(Optional.of(wallet));
        when(walletMapper.toWalletDTO(wallet)).thenReturn(resultMapper);
        when(walletRepository.save(wallet)).thenReturn(wallet);
        WalletDTO walletDTO = WalletDTO.builder().id(Long.parseLong("1")).build();
        BigDecimal amount = new BigDecimal(10);
        WalletDTO result = walletService.subtractBalance(walletDTO, amount);
        assertEquals(result.getBalance(), resultMapper.getBalance());
    }


}
