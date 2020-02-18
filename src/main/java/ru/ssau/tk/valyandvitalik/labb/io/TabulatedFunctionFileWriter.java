package ru.ssau.tk.valyandvitalik.labb.io;

import ru.ssau.tk.valyandvitalik.labb.functions.FunctionCube;
import ru.ssau.tk.valyandvitalik.labb.functions.LinkedListTabulatedFunction;
import sun.plugin.javascript.navig.Link;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new FunctionCube(), 0, 10, 20);
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("output/linked list function.txt"));
FunctionsIO.writeTabulatedFunction(fileWriter, linkedListTabulatedFunction);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
