package mortgage;

import mortgage.model.InputData;
import mortgage.model.Overpayment;
import mortgage.model.RateType;
import mortgage.services.*;
import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, BigDecimal> overpaymentSchema = Map.of(
                5, BigDecimal.valueOf(12000),
                19, BigDecimal.valueOf(10000),
                28, BigDecimal.valueOf(11000),
                64, BigDecimal.valueOf(16000),
                78, BigDecimal.valueOf(18000)
        );

        InputData inputData = new InputData()
                .withamount(new BigDecimal(("296192.11")))
                .withOverpaymentSchema(overpaymentSchema)
                .withmonthsDuration(BigDecimal.valueOf(360))
                .withrateType(RateType.DECREASING)
                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE);

//        dla RATE_TYPE: DECREASING i OVERPAYMENT: REDUCE_RATE naprawic pozostala kwote w ostatniej racied
//        to chyba kwestia przyblize

        PrintingService printingService = new PrintingServiceImpl();
        InterestAmountCalculationService interestAmountCalculationService = new InterestAmountCalculationServiceImpl();
        ResidualAmountCalculationService residualAmountCalculationService = new ResidualAmountCalculationServiceImpl();

        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                TimePointServiceFactory.create(),
                new AmountsCalculationServiceImpl(
                        new ConstantAmountsCalculationServiceImpl(interestAmountCalculationService),
                        new DecreasingAmountsCalculationServiceImpl(interestAmountCalculationService)
                ),
                new OverpaymentCalculationServiceImpl(),
                new ResidualCalculationServiceImpl(residualAmountCalculationService),
                new ReferenceCalculationServiceImpl(residualAmountCalculationService)
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create()
        );
        mortgageCalculationService.calculate(inputData);
    }
}