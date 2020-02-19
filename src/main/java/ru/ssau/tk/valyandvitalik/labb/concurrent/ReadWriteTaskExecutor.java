package ru.ssau.tk.valyandvitalik.labb.concurrent;

import ru.ssau.tk.valyandvitalik.labb.functions.ConstantFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction listTabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 100, 100);
        Thread readThread = new Thread(new ReadTask(listTabulatedFunction));
        Thread writeThread = new Thread(new WriteTask(listTabulatedFunction, 0.5));
        writeThread.start();
        readThread.start();
    }
}
