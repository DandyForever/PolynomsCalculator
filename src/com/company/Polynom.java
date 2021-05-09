package com.company;

import java.util.*;

public class Polynom {
    public Polynom(String input) {
        data = new TreeMap<Integer, Integer>();
        input = input.replaceAll("\\s+", "");
//        StringBuilder sb = new StringBuilder(input);
        if (Character.isDigit(input.charAt(0)) || input.charAt(0) == 'x')
//            sb.insert(0, '+');
            input = "+" + input;
//        sb.append('+');
        input = input + "+";
//        input = sb.toString();
//        System.out.println(input);
        Vector<Integer> positions = new Vector<>();
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))){
                positions.add(i);
            }
        }
        int coef = 0, power = 0;
        boolean is_coef = false, is_power = false;
        for (int i = 1; i < positions.size(); i++){
//            System.out.print(positions.elementAt(i - 1) + 1);
//            System.out.println(input.charAt(positions.elementAt(i - 1)));
//            System.out.print(positions.elementAt(i));
//            System.out.println(input.charAt(positions.elementAt(i)));
            String temp = input.substring(positions.elementAt(i - 1) + 1, positions.elementAt(i));
            if (input.charAt(positions.elementAt(i - 1)) == '+' ||
                input.charAt(positions.elementAt(i - 1)) == '-'){
//                System.out.println("Found sign");
                if (is_coef && !is_power) {
//                    System.out.print(coef);
//                    System.out.println(" is a free coefficient");
                    bias = coef;
                    is_bias_valid = true;
                    is_coef = false;
                }
                if (input.charAt(positions.elementAt(i)) == 'x' &&
                        positions.elementAt(i - 1) + 1 == positions.elementAt(i)){
//                    System.out.println("Coef == 1");
                    coef = 1;
                    is_coef = true;
                } else {
                    coef = Integer.parseInt(temp);
                    is_coef = true;
                }
                if (input.charAt(positions.elementAt(i - 1)) == '-')
                    coef = -coef;
            } else if (input.charAt(positions.elementAt(i - 1)) == '^'){
                power = Integer.parseInt(temp);
                is_power = true;
            } else if (input.charAt(positions.elementAt(i - 1)) == 'x' &&
                !(input.charAt(positions.elementAt(i)) == '^')){
                power = 1;
                is_power = true;
            }
            if (is_coef && is_power){
//                System.out.print(coef);
//                System.out.print(" is coefficient before x in the power of ");
//                System.out.println(power);
                data.put(power, coef);
                is_coef = false;
                is_power = false;
            }
//            System.out.println(temp);
        }
        if (is_coef){
            bias = coef;
            is_bias_valid = true;
        }
//        sortKeys();
    }

    public Polynom(Polynom poly){
        data = poly.getData();
        bias = poly.getBias();
        is_bias_valid = poly.isIs_bias_valid();
        isUnknownConstant = poly.isUnknownConstant();
    }

    private void sortKeys(){
        List<Integer> keys = new LinkedList<>(data.keySet());
        Collections.sort(keys);

        Map<Integer, Integer> sortedData = new TreeMap<>();
        for (Integer key : keys){
            sortedData.put(key, data.get(key));
        }
        data = sortedData;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (isUnknownConstant)
            sb.append('C');
        else if (is_bias_valid)
            sb.append(bias);

        boolean is_first = true;
        Iterator<Integer> it = data.keySet().iterator();
        while (it.hasNext()){
//            System.out.println("Constructing string");
            Integer power = it.next();
            Integer coef  = data.get(power);
            if (coef > 0 && (!is_first || is_bias_valid))
                sb.append('+');
            if (coef == -1)
                sb.append('-');
            else if (coef != 1)
                sb.append(coef);
            sb.append('x');
            if (power != 1) {
                sb.append('^');
                sb.append(power);
            }
            is_first = false;
        }

        return sb.toString();
    }

    public void invalidateBias(){
        bias = 0;
        is_bias_valid = false;
    }

    public void setUnknownConstant(){
        isUnknownConstant = true;
        bias = 0;
    }

    public void setData(Map<Integer, Integer> data_){
        data = data_;
    }
    public void setBias(int bias_){
        bias = bias_;
    }

    public void setIs_bias_valid(boolean flag){
        is_bias_valid = flag;
    }

    public void setUnknownConstant(boolean flag){
        isUnknownConstant = flag;
    }

    public Map<Integer, Integer> getData(){
        return data;
    }

    public int getBias(){
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

        Iterator<Integer> it = data.keySet().iterator();
        Map<Integer, Integer> data_ = new TreeMap<>();
        while (it.hasNext()){
            Integer power = it.next();
            Integer coef  = data.get(power);

            if (power == 1) {
                bias = coef;
                is_bias_valid = true;
                data_.remove(power);
            } else {
                Integer power_ = power - 1;
                data_.put(power_, coef * power);
//                data_.remove(power);
            }
        }
        data = data_;
//        sortKeys();
    }

    public void integrate() {
        Map<Integer, Integer> data_ = new TreeMap<>();
        if (is_bias_valid){
            data_.put(1, bias);
        }

        Iterator<Integer> it = data.keySet().iterator();
        while (it.hasNext()) {
            Integer power = it.next();
            Integer coef = data.get(power);

            Integer power_ = power + 1;
            data_.put(power_, coef / power_);
        }

        data = data_;
        setUnknownConstant();
//        System.out.println(bias);
//        System.out.println(is_bias_valid);
//        System.out.println(isUnknownConstant);
//        sortKeys();
    }

    private Map<Integer, Integer> data;
    private int bias = 0;
    private boolean is_bias_valid = false;
    private boolean isUnknownConstant = false;
}
