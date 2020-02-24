package ru.ssau.tk.valyandvitalik.labb.ui;

import ru.ssau.tk.valyandvitalik.labb.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.valyandvitalik.labb.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FormTable extends JDialog {
    List<Double> xValues = new ArrayList<>();
    List<Double> yValues = new ArrayList<>();
    AbstractTableModel tableModel = new MyTableModel(xValues, yValues);
    JTable table = new JTable(tableModel);
    private JTextField input = new JTextField("", 2);
    private JButton buttonCreate = new JButton("Create");
    TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
    TabulatedFunction tabulatedFunction;
    private JButton buttonView = new JButton("Show table");

    public FormTable(TabulatedFunction tabulatedFunction, int count) {
        setTitle("Table Points");
        setModal(true);
        this.tabulatedFunction = tabulatedFunction;
        this.setBounds(100, 100, 300, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addButtonListener(count);
        composeTable();
        buttonCreate.setEnabled(false);

    }

    void composeTable() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScroll = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup())
                .addComponent(tableScroll)
                .addComponent(buttonView)
                .addComponent(buttonCreate));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                .addComponent(tableScroll)
                .addComponent(buttonView)
                .addComponent(buttonCreate));
    }

    public void addButtonListener(int count) {
        addListenerForButtonCreate(tableModel);
        addListenerForButtonView(tableModel, count);
        addListenerForButton2();
    }

    public void clearTable(int n) {
        for (int i = n; i > 3; i--) {
            xValues.remove(i - n + 1);
            yValues.remove(i - n + 1);
            tableModel.fireTableDataChanged();
        }
    }

    public void addListenerForButtonView(AbstractTableModel tableModel, int count) {
        buttonView.addActionListener(event -> {
            try {
                buttonCreate.setEnabled(false);
                buttonView.setEnabled(true);
                clearTable(tableModel.getColumnCount());
                for (int i = 0; i < count; i++) {
                    xValues.add(0.);
                    yValues.add(0.);
                    tableModel.fireTableDataChanged();
                }
                if (tableModel.getRowCount() > 1) {
                    buttonCreate.setEnabled(true);
                    buttonView.setEnabled(false);
                }
            } catch (Exception e) {
                new ErrorWindow(this, e);

            }
        });
    }

    public void addListenerForButtonCreate(AbstractTableModel tableModel) {
        this.tableModel = tableModel;
        if (tableModel.getRowCount() > 1) {
            buttonCreate.setEnabled(true);
        }
        buttonCreate.addActionListener(event -> {
            try {
                double[] x = new double[xValues.size()];
                double[] y = new double[xValues.size()];
                x[0] = xValues.get(0);
                y[0] = yValues.get(0);
                for (int i = 1; i < xValues.size(); i++) {
                    if (xValues.get(i - 1) > xValues.get(i)) {
                        throw new ArrayIsNotSortedException();
                    }
                    x[i] = xValues.get(i);
                    y[i] = yValues.get(i);
                }
                tabulatedFunction = factory.create(x, y);
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
    }

    public void addListenerForButton2() {
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChanged();
            }
        });
    }

    private void onChanged() {
        buttonCreate.setEnabled(!input.getText().isEmpty());
    }
}
