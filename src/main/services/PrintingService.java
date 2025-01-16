package main.services;

import main.model.InputData;
import main.model.Rate;
import main.model.Summary;

import java.util.List;

public interface PrintingService {
    String INTEREST_SUM = "SUMA ODSETEK: ";
    String OVERPAYMENT_PROVISION = "PROWIZJA ZA NADPLATY: ";
    String LOSTS_SUM = "SUMA STRAT: ";

    String OVERPAYMENT_REDUCE_RATE = "NADPLATA, ZMNIEJSZENIE RATY";
    String OVERPAYMENT_REDUCE_PERIOD = "NADPLATA, SKROCENIE OKRESU";
    String OVERPAYMENT_FREQUENCY = "SCHEMAT DOKONYWANIA NADPLAT:";

    String RATE_NUMBER = "Nr: ";
    String YEAR = " ROK: ";
    String MONTH = " MIESIĄC: ";
    String DATE = " DATA: ";
    String MONTHS = " MIESIĘCY ";
    String RATE = " RATA: ";
    String INTEREST = "ODSETKI: ";
    String CAPITAL = " KAPITAŁ: ";
    String OVERPAYMENT = " NADPŁATA: ";
    String LEFT_AMOUNT = " POZOSTAŁA KWOTA: ";
    String LEFT_MONTHS = " POZOSTAŁE MIESIĄCE: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";

    String CURRENCY = " ZL ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";
    void printInputDataInfo(final InputData inputData);

    void printRates(List<Rate> rates);

    void printSummary(Summary summary);
}
