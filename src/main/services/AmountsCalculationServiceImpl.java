package main.services;

import main.model.InputData;
import main.model.Overpayment;
import main.model.Rate;
import main.model.RateAmounts;
import main.model.exception.RateCalculationException;

import java.math.BigDecimal;

public class AmountsCalculationServiceImpl implements AmountsCalculationService {
    private final ConstantAmountsCalculationService constantAmountsCalculationService;
    private final DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    public AmountsCalculationServiceImpl(ConstantAmountsCalculationService constantAmountsCalculationService,
                                         DecreasingAmountsCalculationService decreasingAmountsCalculationService
    ){
        this.constantAmountsCalculationService = constantAmountsCalculationService;
        this.decreasingAmountsCalculationService = decreasingAmountsCalculationService;
    }

    private static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {

        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData, overpayment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(inputData,overpayment);
            default:
                throw new RateCalculationException();
        }
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {

        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData, overpayment, previousRate);
//                return calculateConstantRate(inputData, overpayment, previousRate);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(inputData,overpayment,previousRate);
//                return calculateDecreasingRate(inputData, overpayment, previousRate);
            default:
                throw new RateCalculationException();
        }
    }
}
