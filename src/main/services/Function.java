package main.services;

import main.model.Rate;

import java.math.BigDecimal;

public interface Function {
    BigDecimal calculate(Rate rate);
}
