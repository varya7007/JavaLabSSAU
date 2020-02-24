package ru.ssau.tk.valyandvitalik.labb.ui;

import ru.ssau.tk.valyandvitalik.labb.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;
import ru.ssau.tk.valyandvitalik.labb.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.valyandvitalik.labb.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;

public class Form extends JDialog {
    List<Double> xValues = new ArrayList<>();
    List<Double> yValues = new ArrayList<>();
    AbstractTableModel tableModel = new MyTableModel(xValues, yValues);
    JTable table = new JTable(tableModel);
    private JButton buttonIn = new JButton("Create table");
    private JButton buttonCreate = new JButton("Create");
    private JButton buttonView = new JButton("Show table");
    private JTextField input = new JTextField("", 2);
    private JLabel label = new JLabel("Input count of point: ");
    TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
    TabulatedFunction tabulatedFunction;

    public Form() {
        setModal(true);
        setTitle("Input");
        this.setBounds(200, 200, 450, 100);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addButtonListener();
        composeInput();
        buttonIn.setEnabled(true);
    }

    public Form(Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        setTitle("Table Points");
        this.setBounds(300, 300, 500, 500);
        addButtonListener(callback);
        composeInput();
        buttonIn.setEnabled(false);
        buttonCreate.setEnabled(false);
    }

    public Form(TabulatedFunction tabulatedFunction) {
        setTitle("Table Points");
        setModal(true);
        this.tabulatedFunction = tabulatedFunction;
        this.setBounds(100, 100, 300, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addButtonListener();
        composeTable();
        buttonView.setEnabled(true);
        buttonCreate.setEnabled(false);

    }

    /*public Form(JTable table) {
        setTitle("Table Points");
        setModal(true);
        this.setBounds(100, 100, 300, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addButtonListener();
        composeTable();
        buttonView.setEnabled(true);
        buttonCreate.setEnabled(false);
    }*/

    void composeInput() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(input)
                        .addComponent(buttonIn)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(input)
                        .addComponent(buttonIn)));
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

    public void addButtonListener(Consumer<? super TabulatedFunction> callback) {
        addListenerForButtonIn();
        addListenerForButtonView();
        addListenerForButtonCreate(callback);
        addListenerForButton2();
    }

    public void addButtonListener() {
        addListenerForButtonIn();
        addListenerForButtonView();
        addListenerForButtonCreate();
        addListenerForButton2();
    }

    public void clearTable(int n) {
        for (int i = n; i > 3; i--) {
            xValues.remove(i - n + 1);
            yValues.remove(i - n + 1);
            tableModel.fireTableDataChanged();
        }
    }

    public void addListenerForButtonIn() {
        buttonIn.addActionListener(new ButtonEventListener());
    }

    public void addListenerForButtonView() {
        buttonView.addActionListener(event -> {
            try {
                buttonCreate.setEnabled(false);
                int count = parseInt(input.getText());
                clearTable(tableModel.getColumnCount());
                for (int i = 0; i < count; i++) {
                    xValues.add(0.);
                    yValues.add(0.);
                    tableModel.fireTableDataChanged();
                }
                if (tableModel.getRowCount() > 1) {
                    buttonCreate.setEnabled(true);
                }
            } catch (Exception e) {
                new ErrorWindow(this, e);

            }
        });
    }

    public void addListenerForButtonCreate() {
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

    public void addListenerForButtonCreate(Consumer<? super TabulatedFunction> callback) {
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
                callback.accept(tabulatedFunction);
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

    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Form app = new Form(tabulatedFunction);
            app.setVisible(true);
        }

    }

    public static void main(String[] args) {
        Form app = new Form();
        app.setVisible((true));
    }

  /*  public static void main(TabulatedFunction func) {
        Form app = new Form(func);
        app.setVisible(true);
    }

    public static void main(Consumer<? super TabulatedFunction> callback) {
        Form app = new Form(callback);
        app.setVisible(true);
    }*/
}