package mortgage.services;

import mortgage.model.InputData;
import mortgage.model.Overpayment;
import mortgage.model.Rate;
import mortgage.model.RateAmounts;

public interface ConstantAmountsCalculationService {
    //    first rate
    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    //    next rates
    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
