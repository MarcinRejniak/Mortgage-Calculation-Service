package main.services;

import main.model.InputData;
import main.model.MortgageResidual;
import main.model.Rate;
import main.model.RateAmounts;

public interface ResidualCalculationService {
    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);

    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData, Rate previousRate);
}
