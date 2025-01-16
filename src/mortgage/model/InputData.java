package mortgage.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class InputData {

    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);

    private LocalDate repaymentStartDate = LocalDate.of(2020, 1, 6);

    private BigDecimal wiborPercent = new BigDecimal("1.70");

    private BigDecimal amount = new BigDecimal("301953.46");

    private BigDecimal monthsDuration = new BigDecimal("180");

    private RateType rateType = RateType.CONSTANT;

    private BigDecimal bankMarginPercent = new BigDecimal("1.6");

    private Map<Integer, BigDecimal> overpaymentSchema = Map.of(
            2, BigDecimal.valueOf(10000),
            3, BigDecimal.valueOf(10000),
            5, BigDecimal.valueOf(10000),
            6, BigDecimal.valueOf(10000),
            7, BigDecimal.valueOf(10000)
    );

    private String overpaymentReduceWay = Overpayment.REDUCE_PERIOD;

    private BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(3);

    private BigDecimal overpaymentProvisionMonths = BigDecimal.valueOf(36);

    public InputData withOverpaymentSchema(Map<Integer, BigDecimal> overpaymentSchema){
        this.overpaymentSchema = overpaymentSchema;
        return this;
    }

    public InputData withOverpaymentReduceWay(String overpaymentReduceWay){
        this.overpaymentReduceWay = overpaymentReduceWay;
        return this;
    }

    public InputData withOverpaymentProvisionPercent(BigDecimal overpaymentProvisionPercent){
        this.overpaymentProvisionPercent = overpaymentProvisionPercent;
        return this;
    }

    public InputData withOverpaymentProvisionMonths(BigDecimal overpaymentProvisionMonths){
        this.overpaymentProvisionMonths = overpaymentProvisionMonths;
        return this;
    }


    public InputData withrepaymentStartDate(LocalDate repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
        return this;
    }

    public InputData withWiborPercent(BigDecimal wiborPercent) {
        this.wiborPercent = wiborPercent;
        return this;
    }

    public InputData withamount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public InputData withmonthsDuration(BigDecimal monthsDuration) {
        this.monthsDuration = monthsDuration;
        return this;
    }

    public InputData withrateType(RateType rateType) {
        this.rateType = rateType;
        return this;
    }

    public InputData withBankMarginPercent(BigDecimal bankMargin) {
        this.bankMarginPercent = bankMargin;
        return this;
    }

    public LocalDate getRepaymentStartDate() {
        return repaymentStartDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMonthsDuration() {
        return monthsDuration;
    }

    public RateType getRateType() {
        return rateType;
    }

    public BigDecimal getInterestPercent() {
        return wiborPercent.add(bankMarginPercent).divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }

    public Map<Integer, BigDecimal> getOverpaymentSchema() {
        return overpaymentSchema;
    }

    public String getOverpaymentReduceWay() {
        return overpaymentReduceWay;
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return overpaymentProvisionPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentProvisionMonths() {
        return overpaymentProvisionMonths;
    }
}
