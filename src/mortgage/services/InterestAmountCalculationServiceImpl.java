package mortgage.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static mortgage.services.TimePointServiceFactory.YEAR;

public class InterestAmountCalculationServiceImpl implements InterestAmountCalculationService {
    @Override
    public BigDecimal calculate(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }
}
