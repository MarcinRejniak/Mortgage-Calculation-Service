package main.services;

import main.model.InputData;
import main.model.MortgageResidual;
import main.model.Rate;
import main.model.RateAmounts;
import main.model.exception.MortgageException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResidualCalculationServiceImpl implements ResidualCalculationService{
    private final ResidualAmountCalculationService residualAmountCalculationService;

    public ResidualCalculationServiceImpl(ResidualAmountCalculationService residualAmountCalculationService) {
        this.residualAmountCalculationService = residualAmountCalculationService;
    }

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData) {
        if(BigDecimal.ZERO.equals(inputData.getAmount())){
            return new MortgageResidual(BigDecimal.ZERO, BigDecimal.ZERO);
        }
        BigDecimal residualAmount = calculateResidualAmount(inputData.getAmount(), rateAmounts);
        BigDecimal residualDuration = calculateResidualDuration(inputData, residualAmount, inputData.getMonthsDuration(), rateAmounts);

        return new MortgageResidual(residualAmount,residualDuration);
    }

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, final InputData inputData, Rate previousRate) {
        BigDecimal previousResidualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal previousResidualDuration = previousRate.getMortgageResidual().getDuration();

        if(BigDecimal.ZERO.equals(previousResidualAmount)){
            return new MortgageResidual(BigDecimal.ZERO,BigDecimal.ZERO);
        }else{
            BigDecimal residualAmount = calculateResidualAmount(previousResidualAmount, rateAmounts);
            BigDecimal residualDuration = calculateResidualDuration(
                    inputData,
                    residualAmount,
                    previousResidualDuration,
                    rateAmounts
            );
            return new MortgageResidual(residualAmount, residualDuration);
        }
    }

    private static BigDecimal calculateResidualAmount(final BigDecimal residualAmount, final RateAmounts rateAmounts) {
        return residualAmount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }

    private BigDecimal calculateResidualDuration(
            InputData inputData,
            BigDecimal residualAmount,
            BigDecimal previousResidualDuration,
            RateAmounts rateAmounts)
    {

        if(rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO)>0){
            switch (inputData.getRateType()) {
                case CONSTANT -> {
                    return calculateConstantResidualDuration(inputData, residualAmount, rateAmounts, previousResidualDuration);
                }
                case DECREASING -> {
                    return calculateDecreasingResidualDuration(inputData, residualAmount, rateAmounts, previousResidualDuration);
                }
                default -> {
                    throw new MortgageException("Case not handled");
                }
            }
        }else{
            return previousResidualDuration.subtract(BigDecimal.ONE);
        }
    }

    private BigDecimal calculateDecreasingResidualDuration(InputData inputData, BigDecimal residualAmount, RateAmounts rateAmounts, BigDecimal previousResidualDuration) {
        switch (inputData.getOverpaymentReduceWay()){
            case "REDUCE_RATE":
                return previousResidualDuration.subtract(BigDecimal.ONE);
            case "REDUCE_PERIOD":
                return residualAmount.divide(rateAmounts.getCapitalAmount(), 0, RoundingMode.CEILING);
            default:
            throw new main.services.MortgageException();
        }
    }

    private BigDecimal calculateConstantResidualDuration(InputData inputData, BigDecimal residualAmount, RateAmounts rateAmounts, BigDecimal previousResidualDuration) {
        switch (inputData.getOverpaymentReduceWay()){
            case "REDUCE_RATE":
                return previousResidualDuration.subtract(BigDecimal.ONE);
            case "REDUCE_PERIOD":
                BigDecimal q = AmountsCalculationService.calculateQ(inputData.getInterestPercent());
                BigDecimal xNumerator = rateAmounts.getRateAmount();
                BigDecimal xDenominator = rateAmounts.getRateAmount().subtract(residualAmount.multiply(q.subtract(BigDecimal.ONE)));
                BigDecimal x = xNumerator.divide(xDenominator, 10, RoundingMode.HALF_UP);
                BigDecimal y = q;
                BigDecimal logX = BigDecimal.valueOf(Math.log(x.doubleValue()));
                BigDecimal logY = BigDecimal.valueOf(Math.log(y.doubleValue()));

                return logX.divide(logY, 0, RoundingMode.CEILING);
            default:
                throw new main.services.MortgageException();
        }
    }
}
