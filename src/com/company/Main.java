package com.company;

public class Main {

    public static void main(String[] args) {
        Polynom sample = new Polynom("2/5+1/4x+5/6x^2/3");
//        sample.derivate();
        sample.integrate();
        System.out.println(sample.toString());
    }
}
