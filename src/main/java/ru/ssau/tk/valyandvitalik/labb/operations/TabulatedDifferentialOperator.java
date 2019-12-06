package ru.ssau.tk.valyandvitalik.labb.operations;

import ru.ssau.tk.valyandvitalik.labb.functions.Point;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    TabulatedFunctionFactory factory;

    TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] arrayPoint = TabulatedFunctionOperationService.asPoint(function);
        double[] xValues = new double[arrayPoint.length];
        double[] yValues = new double[arrayPoint.length];
        for (int i = 0; i < xValues.length; i++) {
            xValues[i] = arrayPoint[i].x;
        }
        for (int i = 0; i < xValues.length - 1; i++) {
            yValues[i] = (arrayPoint[i + 1].y - arrayPoint[i].y) / (xValues[i + 1] - xValues[i]);
        }
        yValues[xValues.length - 1] = yValues[xValues.length - 2];
        return factory.create(xValues, yValues);
    }


    @Override
    public double apply(double x) {
        return 0;
    }
}
