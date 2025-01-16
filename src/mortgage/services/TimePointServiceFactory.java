package mortgage.services;

import mortgage.model.InputData;
import mortgage.model.TimePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimePointServiceFactory {

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    public static TimePointService create() {
        return (rateNumber, inputData) -> new TimePoint(getDate(rateNumber, inputData),
                getYear(rateNumber),
                getMonth(rateNumber));
    }

    private static BigDecimal getMonth(BigDecimal rateNumber) {
        return BigDecimal.ZERO.equals(rateNumber.remainder(YEAR)) ? YEAR : rateNumber.remainder(YEAR);
    }

    private static BigDecimal getYear(BigDecimal rateNumber) {
        return rateNumber.divide(YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }

    private static LocalDate getDate(BigDecimal rateNumber, InputData inputData) {
        return inputData.getRepaymentStartDate()
                .plus(rateNumber.subtract(BigDecimal.ONE).intValue(), ChronoUnit.MONTHS);
    }
}
