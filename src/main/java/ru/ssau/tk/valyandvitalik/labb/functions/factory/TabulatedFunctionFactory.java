package ru.ssau.tk.valyandvitalik.labb.functions.factory;

import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
