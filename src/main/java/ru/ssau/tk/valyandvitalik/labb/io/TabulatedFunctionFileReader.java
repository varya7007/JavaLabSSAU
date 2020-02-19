package ru.ssau.tk.valyandvitalik.labb.io;

import ru.ssau.tk.valyandvitalik.labb.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        try (BufferedReader readerLink = new BufferedReader(new FileReader("input/function.txt"))) {
            System.out.println(FunctionsIO.readTabulatedFunction(readerLink, new LinkedListTabulatedFunctionFactory()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
