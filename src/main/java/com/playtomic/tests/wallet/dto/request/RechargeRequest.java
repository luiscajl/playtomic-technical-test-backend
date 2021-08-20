package com.playtomic.tests.wallet.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RechargeRequest {

    private String cardPan;

    private BigDecimal amount;

}
