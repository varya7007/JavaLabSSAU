package ru.ssau.tk.valyandvitalik.labb.operations;

import ru.ssau.tk.valyandvitalik.labb.functions.Point;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoint(TabulatedFunction tabulatedFunction) {
        Point points[] = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point newPoint : tabulatedFunction) {
            points[i++] = newPoint;
        }
        return points;
    }
}
