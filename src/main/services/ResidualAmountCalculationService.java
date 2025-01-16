package main.services;

import main.model.RateAmounts;

import java.math.BigDecimal;

public interface ResidualAmountCalculationService {
    BigDecimal calculate(RateAmounts rateAmounts, BigDecimal amount);
}
