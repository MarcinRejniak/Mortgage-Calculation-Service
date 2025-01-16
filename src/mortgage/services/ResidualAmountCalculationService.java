package mortgage.services;

import mortgage.model.RateAmounts;

import java.math.BigDecimal;

public interface ResidualAmountCalculationService {
    BigDecimal calculate(RateAmounts rateAmounts, BigDecimal amount);
}
