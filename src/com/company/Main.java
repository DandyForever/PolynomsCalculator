package com.company;

public class Main {

    public static void main(String[] args) {
        Polynom sample = new Polynom("2+2x+3x^2");
//        sample.derivate();
        sample.integrate();
        System.out.println(sample.toString());
    }
}
