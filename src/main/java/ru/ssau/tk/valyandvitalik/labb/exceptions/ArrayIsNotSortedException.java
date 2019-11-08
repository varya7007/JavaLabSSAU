package ru.ssau.tk.valyandvitalik.labb.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String throwMessage) {
        super(throwMessage);
    }
}
