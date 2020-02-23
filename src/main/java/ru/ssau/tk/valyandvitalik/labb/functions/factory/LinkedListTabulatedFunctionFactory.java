package ru.ssau.tk.valyandvitalik.labb.functions.factory;

import ru.ssau.tk.valyandvitalik.labb.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
