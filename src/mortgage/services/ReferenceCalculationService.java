package mortgage.services;

import mortgage.model.InputData;
import mortgage.model.MortgageReference;
import mortgage.model.Rate;
import mortgage.model.RateAmounts;

public interface ReferenceCalculationService {
    MortgageReference calculate(RateAmounts rateAmounts, InputData inputData);

    MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}
