package com.company;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator extends Applet implements ActionListener {
    TextField input;
    TextField output;
    Button derivate;
    Button integrate;
    public void init(){
        Font font = new Font("Calibri", Font.PLAIN, 50);
        input = new TextField();
        input.setFont(font);
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != 'x' && c != '^' && c != '+' && c != '-' && c != '/')
                    e.consume();
            }
        });
        output = new TextField();
        output.setFont(font);
        output.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
            }
        });
        derivate = new Button("Derivate!");
        derivate.setFont(font);
        derivate.addActionListener(this);
        integrate = new Button("Integrate!");
        integrate.setFont(font);
        integrate.addActionListener(this);
        Panel buttons = new Panel();
        buttons.add(derivate);
        buttons.add(integrate);
        buttons.setLayout(new GridLayout(1, 2));
        setLayout(new GridLayout(3,2));
//        add(title);
        add(input);
        add(buttons);
        add(output);
        setSize(1000, 600);
    }

    public void actionPerformed(ActionEvent ae){
        Polynom poly = new Polynom(input.getText());
        if (ae.getSource() == derivate)
            poly.derivate();
        else if (ae.getSource() == integrate)
            poly.integrate();
        output.setText(poly.toString());
    }
}
