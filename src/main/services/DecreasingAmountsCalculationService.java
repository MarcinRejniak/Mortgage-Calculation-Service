package main.services;

import main.model.InputData;
import main.model.Overpayment;
import main.model.Rate;
import main.model.RateAmounts;

public interface DecreasingAmountsCalculationService {
    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
