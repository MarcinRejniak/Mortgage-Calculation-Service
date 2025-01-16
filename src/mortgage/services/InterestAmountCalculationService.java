package mortgage.services;

import java.math.BigDecimal;

public interface InterestAmountCalculationService {

    BigDecimal calculate(BigDecimal residualAmount, BigDecimal interestPercent);
}
