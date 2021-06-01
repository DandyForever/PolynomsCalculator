package com.company;

import java.util.*;

public class Polynom {
    public Polynom(String input) {
        data = new TreeMap<>();
        input = input.replaceAll("\\s+", "");
        if (Character.isDigit(input.charAt(0)) || input.charAt(0) == 'x')
            input = "+" + input;
        input = input + "+";
        Vector<Integer> positions = new Vector<>();
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '/'){
                if (!(input.charAt(i) == '-' && i != 0 && input.charAt(i - 1) == '^'))
                    positions.add(i);
            }
        }
        Rational coef = new Rational(0, 1), power = new Rational(0, 1);
        boolean is_coef = false, is_power = false;
        int i = 1;
        while (i < positions.size()){
            String temp = input.substring(positions.elementAt(i - 1) + 1, positions.elementAt(i));
            if (input.charAt(positions.elementAt(i - 1)) == '+' ||
                input.charAt(positions.elementAt(i - 1)) == '-'){
                if (is_coef && !is_power) {
                    bias = coef;
                    is_bias_valid = true;
                    is_coef = false;
                }
                if (input.charAt(positions.elementAt(i)) == 'x' &&
                        positions.elementAt(i - 1) + 1 == positions.elementAt(i)){
                    coef = new Rational(1, 1);
                    is_coef = true;
                } else {
                    coef = new Rational(temp);

                    is_coef = true;
                }
                if (input.charAt(positions.elementAt(i - 1)) == '-')
                    coef.invert();
            } else if (input.charAt(positions.elementAt(i - 1)) == '^'){
                power = new Rational(temp);
                is_power = true;
            } else if (input.charAt(positions.elementAt(i - 1)) == 'x' &&
                !(input.charAt(positions.elementAt(i)) == '^')){
                power = new Rational(1, 1);
                is_power = true;
            }
            if (is_coef && is_power){
                data.put(power, coef);
                is_coef = false;
                is_power = false;
            }
            i++;
        }
        if (is_coef){
            bias = coef;
            is_bias_valid = true;
        }
    }

    public Polynom(Polynom poly){
        data = poly.getData();
        bias = poly.getBias();
        is_bias_valid = poly.isIs_bias_valid();
        isUnknownConstant = poly.isUnknownConstant();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (isUnknownConstant)
            sb.append('C');
        else if (is_bias_valid)
            sb.append(bias);

        boolean is_first = true;
        Iterator<Rational> it = data.keySet().iterator();
        while (it.hasNext()){
            Rational power = it.next();
            Rational coef  = data.get(power);
            if (coef.getNumerator() > 0 && (!is_first || is_bias_valid || isUnknownConstant))
                sb.append('+');
            if (coef.equal(-1))
                sb.append('-');
            else if (!coef.equal(1))
                sb.append(coef);
            sb.append('x');
            if (!power.equal(1)) {
                sb.append('^');
                sb.append(power);
            }
            is_first = false;
        }

        return sb.toString();
    }

    public void invalidateBias(){
        bias = new Rational(0, 1);
        is_bias_valid = false;
    }

    public void setUnknownConstant(){
        isUnknownConstant = true;
        bias = new Rational(0, 1);
    }

    public Map<Rational, Rational> getData(){
        return data;
    }

    public Rational getBias(){
        return bias;
    }

    public boolean isIs_bias_valid(){
        return is_bias_valid;
    }

    public boolean isUnknownConstant(){
        return isUnknownConstant;
    }

    public void derivate(){
        invalidateBias();

        Iterator<Rational> it = data.keySet().iterator();
        Map<Rational, Rational> data_ = new TreeMap<>();
        while (it.hasNext()){
            Rational power = it.next();
            Rational coef  = data.get(power);

            if (power.equal(1)) {
                bias = coef;
                is_bias_valid = true;
                data_.remove(power);
            } else {
                Rational power_ = new Rational(power);
                power_.sub(1);
                Rational coef_ = new Rational(coef);
                coef_.mul(power);
                data_.put(power_, coef_);
            }
        }
        data = data_;
    }

    public void integrate() {
        Map<Rational, Rational> data_ = new TreeMap<>();
        if (is_bias_valid){
            data_.put(new Rational(1, 1), bias);
        }

        Iterator<Rational> it = data.keySet().iterator();
        while (it.hasNext()) {
            Rational power = it.next();
            Rational coef = data.get(power);
            Rational power_ = new Rational(power);
            power_.add(1);
            Rational coef_ = new Rational(coef);
            coef_.div(power_);
            data_.put(power_, coef_);
        }

        data = data_;
        setUnknownConstant();
    }

    private Map<Rational, Rational> data;
    private Rational bias = new Rational(0, 1);
    private boolean is_bias_valid = false;
    private boolean isUnknownConstant = false;
}
