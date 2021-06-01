package com.company;

import java.io.Serializable;

import static java.lang.Math.abs;

public class Rational implements Comparable<Rational>{
    private Integer numerator;
    private Integer denominator;

    public Rational(String input){
        boolean is_negative = input.indexOf('-') == 0;
        if (is_negative)
            input = input.substring(1);
        int divider_index = input.indexOf('/');
        if (divider_index == -1){
            numerator = Integer.parseInt(input);
            denominator = 1;
        } else {
            numerator = Integer.parseInt(input.substring(0, divider_index));
            denominator = Integer.parseInt(input.substring(divider_index + 1));
        }
        simplify();
        if (is_negative)
            numerator = -numerator;
    }

    public Rational(int num, int den){
        if (den == 0){
            numerator = 0;
            denominator = 0;
            return;
        }
        if (num == 0){
            numerator = 0;
            denominator = 1;
            return;
        }
        numerator = num;
        denominator = den;
        if (den < 0)
            numerator *= -1;
        simplify();
    }

    public Rational(Rational that){
        numerator = that.getNumerator();
        denominator = that.getDenominator();
    }

    private int gcd(int first, int second){
        if (first < second) {
            if (second % first == 0)
                return first;
            return gcd(second % first, first);
        }
        if (first % second == 0)
            return second;
        return gcd(second, first % second);
    }

    private void simplify(){
        int gcd = gcd(abs(numerator), abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(numerator);
        if (denominator == 1)
            return sb.toString();
        sb.append('/');
        sb.append(denominator);
        return sb.toString();
    }

    public void add(int number){
        numerator += number * denominator;
        simplify();
    }

    public void sub(int number){
        numerator -= number * denominator;
        simplify();
    }

    public void mul(Rational number){
        numerator *= number.getNumerator();
        denominator *= number.getDenominator();
        simplify();
    }

    public void div(Rational number){
        denominator *= number.getNumerator();
        numerator *= number.getDenominator();
        simplify();
    }

    public boolean equal(int number){
        if (denominator == 1)
            return numerator == number;
        return false;
    }

    public void invert(){
        numerator = -numerator;
    }

    public Integer getNumerator(){
        return numerator;
    }

    public Integer getDenominator(){
        return denominator;
    }


    @Override
    public int compareTo(Rational o) {
        return Integer.compare(o.getDenominator() * numerator, o.getNumerator() * denominator);
    }
}
