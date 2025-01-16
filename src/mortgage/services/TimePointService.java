package mortgage.services;

import mortgage.model.InputData;
import mortgage.model.TimePoint;

import java.math.BigDecimal;

public interface TimePointService {
    TimePoint calculate(BigDecimal rateNumber, InputData inputData);
}
