package ru.ssau.tk.valyandvitalik.labb.functions;

import ru.ssau.tk.valyandvitalik.labb.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.valyandvitalik.labb.exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    abstract int floorIndexOfX(double x);

    abstract double extrapolateLeft(double x);

    abstract double extrapolateRight(double x);

    abstract double interpolate(double x, int floorIndex);

    double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorIndexOfX(x));
        }
    }

    static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException();
        }
    }

    static void checkSorted(double[] xValues) {
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1]) {
                throw new ArrayIsNotSortedException();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder newString = new StringBuilder();
        newString.append(getClass().getSimpleName()).append(" size = ").append(this.getCount());
        for (Point newPoint : this) {
            newString.append("\n").append("[").append(newPoint.x).append(";").append(" ").append("]");
        }
        return newString.toString();
    }
}
