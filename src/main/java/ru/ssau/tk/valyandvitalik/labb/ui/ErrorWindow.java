package ru.ssau.tk.valyandvitalik.labb.ui;

import ru.ssau.tk.valyandvitalik.labb.exceptions.ArrayIsNotSortedException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ErrorWindow {

    ErrorWindow(Component parent, Exception e) {
        showErrorWindow(parent, e);
    }

    public void showErrorWindow(Component parent, Exception e) {
        String head = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Error!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Expected: Number, Found: String";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Array is not sorted";
        }
        if (e instanceof IOException) {
            return "File is corrupted";
        }
        /*if (e instanceof InconsistentFunctionsException) {
            return "xValues are different";
        }*/
        if (e instanceof IllegalArgumentException) {
            return "That is not a valid function";
        }
        return "Unknown error";
    }

}