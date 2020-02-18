package ru.ssau.tk.valyandvitalik.labb.io;

import ru.ssau.tk.valyandvitalik.labb.functions.Point;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) throws IOException {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        writer.flush();
    }
}
