package main.services;

import main.model.InputData;
import main.model.Overpayment;
import main.model.Rate;
import main.model.RateAmounts;

public interface ConstantAmountsCalculationService {
    //    first rate
    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    //    next rates
    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
