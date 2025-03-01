package main.services;

import main.model.*;

import java.math.BigDecimal;

public class ReferenceCalculationServiceImpl implements ReferenceCalculationService {
    private final ResidualAmountCalculationService residualAmountCalculationService;

    public ReferenceCalculationServiceImpl(ResidualAmountCalculationService residualAmountCalculationService) {
        this.residualAmountCalculationService = residualAmountCalculationService;
    }

    @Override
    public MortgageReference calculate(RateAmounts rateAmounts, InputData inputData) {
        if (BigDecimal.ZERO.equals(inputData.getAmount())) {
            return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return new MortgageReference(inputData.getAmount(), inputData.getMonthsDuration());
    }

    @Override
    public MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate) {
        if (BigDecimal.ZERO.equals(previousRate.getMortgageResidual().getAmount())) {
            return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
        }

        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                return new MortgageReference(inputData.getAmount(), inputData.getMonthsDuration());
            case Overpayment.REDUCE_RATE:
                return reduceRateMortgageReference(rateAmounts, previousRate);
            default:
                throw new MortgageException();
        }
    }

    private MortgageReference reduceRateMortgageReference(RateAmounts rateAmounts, Rate previousRate) {
        if (rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal referenceAmount = residualAmountCalculationService.calculate(
                    rateAmounts,
                    previousRate.getMortgageResidual().getAmount()
            );
            BigDecimal referenceDuration = previousRate.getMortgageResidual().getDuration().subtract(BigDecimal.ONE);

            return new MortgageReference(referenceAmount,referenceDuration);
        }

        return new MortgageReference(
                previousRate.getMortgageReference().getAmount(),
                previousRate.getMortgageReference().getDuration()
        );
    }

    private BigDecimal calculateResidualAmount(BigDecimal amount, RateAmounts rateAmounts) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
