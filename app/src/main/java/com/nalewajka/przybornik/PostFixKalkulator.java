package com.nalewajka.przybornik;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostFixKalkulator {
    private List<String> expression = new ArrayList<String>();
    private Deque<Double> stack = new ArrayDeque<Double>();

    public PostFixKalkulator(List<String> postfix) {
        expression = postfix;
    }

    public BigDecimal result() {
        System.out.println(expression);

        for (int i = 0; i != expression.size(); ++i) {
            double tempResult =0;
            // Determine if current element is digit or not
            if (Character.isDigit(expression.get(i).charAt(0))) {

                stack.addLast(Double.parseDouble(expression.get(i)));
            } else {
                double temp;
                if(stack.size()>1 || expression.get(i).equals("^")  ||  expression.get(i).equals("c") || expression.get(i).equals("t") || expression.get(i).equals("s") || expression.get(i).equals("q")  || expression.get(i).equals("f") || expression.get(i).equals("l") || expression.get(i).equals("%")) {
                    switch (expression.get(i)) {
                        case "+":
                            temp = stack.removeLast();
                            tempResult = stack.removeLast() + temp;
                            break;
                        case "-":
                            temp = stack.removeLast();
                            tempResult = stack.removeLast() - temp;
                            break;
                        case "*":
                            temp = stack.removeLast();
                            tempResult = stack.removeLast() * temp;
                            break;
                        case "/":
                            temp = stack.removeLast();
                            tempResult = stack.removeLast() / temp;
                            break;
                        case "%":
                            if (stack.size() == 1) {
                                tempResult = stack.removeLast() / 100;
                            } else {
                                temp = stack.removeLast();
                                tempResult = resultProcentages(temp);
                            }
                            break;
                        case "^":
                            if (stack.size() == 1) tempResult = Math.pow(stack.removeLast(), 2);
                            else {
                                temp = stack.removeLast();
                                tempResult = Math.pow(stack.removeLast(), temp);
                            }
                            break;
                        case "c":
                            temp = stack.removeLast();
                            tempResult = Math.cos(temp);
                            break;
                        case "s":
                            System.out.println("jestem w sin");
                            temp = stack.removeLast();
                            tempResult = Math.sin(temp);
                            break;
                        case "t":
                            temp = stack.removeLast();
                            tempResult = Math.tan(temp);
                            break;
                        case "l":
                            temp = stack.removeLast();
                            tempResult = Math.log(temp);
                            break;
                        case "q":
                            temp = stack.removeLast();
                            tempResult = Math.sqrt(temp);
                            break;
                        case "m":
                            temp = stack.removeLast();
                            tempResult = stack.removeLast() % temp;
                            break;
                        case "f":
                            if (stack.removeLast()>0){
                                tempResult = factorial(stack.removeLast().intValue());}
                            else  tempResult=stack.removeLast();
                            break;
                    }
                }
                else {if (stack.size()>0 && !(expression.get(0).equals(".")))tempResult= stack.removeLast();}
                stack.addLast(tempResult);
            }
        }
        return new BigDecimal(stack.removeLast()).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public double resultProcentages(double numberr) {
        return 100 / stack.removeLast() * numberr;
    }

    int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }
}