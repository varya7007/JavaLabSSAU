package ru.ssau.tk.valyandvitalik.labb.io;

import ru.ssau.tk.valyandvitalik.labb.functions.Point;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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

/*
    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count;
        try {
            count = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        String s;
        for (int i = 0; i < count; i++) {
            s = reader.readLine();
            try {
                xValues[i] = numFormat.parse(s.split(" ")[0]).doubleValue();
                yValues[i] = numFormat.parse(s.split(" ")[1]).doubleValue();
            } catch (ParseException p) {
                throw new IOException(p);
            }
            return factory.create(xValues, yValues);
        }
        return null;
    }*/
public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
    int count;
    try {
        count = Integer.parseInt(reader.readLine());
    } catch (NumberFormatException nfe) {
        throw new IOException(nfe);
    }
    double[] xValues = new double[count];
    double[] yValues = new double[count];
    NumberFormat formatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
    String tempString;
    for (int i = 0; i < count; i++) {
        tempString = reader.readLine();
        try {
            xValues[i] = formatter.parse(tempString.split(" ")[0]).doubleValue();
            yValues[i] = formatter.parse(tempString.split(" ")[1]).doubleValue();
        } catch (ParseException pe) {
            throw new IOException(pe);
        }
    }
    return factory.create(xValues, yValues);
}

   /* public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int count = dataInputStream.readInt();
        boolean isStrict = dataInputStream.readBoolean();
        boolean isUnmodifiable = dataInputStream.readBoolean();
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }
        TabulatedFunction function = factory.create(xValues, yValues);
        function.offerStrict(isStrict);
        function.offerUnmodifiable(isUnmodifiable);
        return function;
    }*/
}
