package main.services;

import main.model.RateAmounts;

import java.math.BigDecimal;

public class ResidualAmountCalculationServiceImpl implements ResidualAmountCalculationService{

    @Override
    public BigDecimal calculate(RateAmounts rateAmounts, BigDecimal amount) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
