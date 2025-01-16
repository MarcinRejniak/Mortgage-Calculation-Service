package main.services;

import main.model.InputData;
import main.model.MortgageReference;
import main.model.Rate;
import main.model.RateAmounts;

public interface ReferenceCalculationService {
    MortgageReference calculate(RateAmounts rateAmounts, InputData inputData);

    MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}
