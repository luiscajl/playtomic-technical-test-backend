package com.playtomic.tests.wallet.dto.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletDTO {

    private Long id;

    private BigDecimal balance;
}
