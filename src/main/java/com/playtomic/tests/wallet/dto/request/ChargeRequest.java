package com.playtomic.tests.wallet.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ChargeRequest {

    private BigDecimal amount;

}
