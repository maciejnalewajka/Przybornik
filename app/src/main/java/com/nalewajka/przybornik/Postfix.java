package com.nalewajka.przybornik;
/*
Kalkulator.java, PostFixCalculator.java, PostFixConverter.java
created by Oskar Kufel
edited by Maciej Nalewajka
*/
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Postfix {
        private final String infix;
        private final Deque<Character> stack = new ArrayDeque<>();
        private final List<String> postfix = new ArrayList<>();

        public Postfix(String expression) {
            infix = expression;
            convertExpression();
        }

        private void convertExpression() {
            StringBuilder temp = new StringBuilder();

            for (int i = 0; i != infix.length(); ++i) {
                if  (String.valueOf(infix.charAt(0)).equals(".")){
                    i++;
                    break;
                }
                if (Character.isDigit(infix.charAt(i))) {
                    temp.append(infix.charAt(i));
                    while ((i + 1) != infix.length() && (Character.isDigit(infix.charAt(i + 1))
                            || infix.charAt(i + 1) == '.')) {
                        temp.append(infix.charAt(++i));
                    }
                    postfix.add(temp.toString());
                    temp.delete(0, temp.length());
                }
                else {
                    inputToStack(infix.charAt(i));
                }
            }
            clearStack();
        }

        private void inputToStack(char input) {
            if (stack.isEmpty() || input == '(')
                stack.addLast(input);
            else {
                if (input == ')') {
                    while (!stack.getLast().equals('(')) {
                        postfix.add(stack.removeLast().toString());
                    }
                    stack.removeLast();
                } else {
                    while (!stack.isEmpty() && !stack.getLast().equals('(') &&
                            getPrecedence(input) <= getPrecedence(stack.getLast())) {
                        postfix.add(stack.removeLast().toString());
                    }
                    stack.addLast(input);
                }
            }
        }

        private int getPrecedence(char op) {
            if (op == '+' || op == '-')
                return 1;
            else if (op == '*' || op == '/')
                return 2;
            else if (op == '^')
                return 3;
            else return 0;
        }

        private void clearStack() {
            while (!stack.isEmpty()) {
                postfix.add(stack.removeLast().toString());
            }
        }

        public List<String> getPostfixAsList() {
            return postfix;
        }
    }