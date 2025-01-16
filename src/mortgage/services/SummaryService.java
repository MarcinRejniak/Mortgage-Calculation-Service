package mortgage.services;

import mortgage.model.Rate;
import mortgage.model.Summary;

import java.util.List;

public interface SummaryService {
    Summary calculate(List<Rate> rates);
}
