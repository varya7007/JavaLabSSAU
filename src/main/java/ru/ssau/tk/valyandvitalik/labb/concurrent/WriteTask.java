package ru.ssau.tk.valyandvitalik.labb.concurrent;

import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;

public class WriteTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    private double value;

    WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.tabulatedFunction = tabulatedFunction;
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            System.out.printf("Writing for index %d complete", i);
        }
    }
}
