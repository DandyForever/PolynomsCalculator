package com.company;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends Applet implements ActionListener {
//    public Polynom derivate(Polynom poly){
//        Polynom derivative = new Polynom(poly);
//        derivative.derivate();
//        return derivative;
//    }
//
//    public Polynom integrate(Polynom poly){
//        return poly;
//    }
    TextField input;
    TextField output;
    Button derivate;
    Button integrate;
    public void init(){
//        Label title = new Label("Polynom derivator and integrator");
//        title.setAlignment();
        Font font = new Font("Calibri", Font.PLAIN, 50);
        input = new TextField();
        input.setFont(font);
        output = new TextField();
        output.setFont(font);
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
