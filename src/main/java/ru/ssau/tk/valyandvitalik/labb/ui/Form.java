package ru.ssau.tk.valyandvitalik.labb.ui;

import ru.ssau.tk.valyandvitalik.labb.functions.TabulatedFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static java.lang.Integer.parseInt;

public class Form extends JDialog {
    private JButton buttonIn = new JButton("Create table");
    private JTextField input = new JTextField("", 2);
    private JLabel label = new JLabel("Input count of point: ");
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

    public void addButtonListener() {
        addListenerForButtonIn();
    }

    public void addListenerForButtonIn() {
        buttonIn.addActionListener(event -> {
            try {
                int count = parseInt(input.getText());
                buttonIn.addActionListener(new ButtonEventListener(count));
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });

    }

    class ButtonEventListener implements ActionListener {
        public ButtonEventListener(int count) {
            FormTable app = new FormTable(tabulatedFunction, count);
            app.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public static void main(String[] args) {
        Form app = new Form();
        app.setVisible((true));
    }
}
