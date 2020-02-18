package ru.ssau.tk.valyandvitalik.labb.operations;

import ru.ssau.tk.valyandvitalik.labb.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}



