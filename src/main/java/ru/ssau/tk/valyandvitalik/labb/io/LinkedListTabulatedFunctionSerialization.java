package ru.ssau.tk.valyandvitalik.labb.io;

import ru.ssau.tk.valyandvitalik.labb.functions.FunctionCube;
import ru.ssau.tk.valyandvitalik.labb.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.valyandvitalik.labb.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new FunctionCube(), 0, 20, 21);
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction firstDerive = differentialOperator.derive(tabulatedFunction);
        TabulatedFunction secondDerive = differentialOperator.derive(firstDerive);
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            FunctionsIO.serialize(outputStream, tabulatedFunction);
            FunctionsIO.serialize(outputStream, firstDerive);
            FunctionsIO.serialize(outputStream, secondDerive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(inputStream).toString());
            System.out.println(FunctionsIO.deserialize(inputStream).toString());
            System.out.println(FunctionsIO.deserialize(inputStream).toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
