package org.example;

import java.util.Stack;
import java.util.Vector;

public class SimpleCalculatorBuilder implements CalculatorBuilderInterface {

    private final Vector<String> arrayStrings = new Vector<>();
    private static SimpleCalculatorBuilder instance;
    private SimpleCalculatorBuilder(){}

    public static SimpleCalculatorBuilder getInstance(){
        if(instance == null){
            instance = new SimpleCalculatorBuilder();
        }
        return instance;
    }
    @Override
    public CalculatorBuilderInterface calculate(Vector<String> exp) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder result= new StringBuilder();




        for (String string : exp) {
            char[] characters = string.toCharArray();

            for (int j = 0; j < characters.length; j++) {
                if (Character.isDigit(characters[j])) {
                    StringBuilder sb = new StringBuilder();
                    while (j < characters.length && (Character.isDigit(characters[j]) || characters[j] == '.')) {
                        sb.append(characters[j++]);
                    }
                    values.push(Double.parseDouble(sb.toString()));
                    j--;
                } else if (characters[j] == '(') {
                    operators.push(characters[j]);
                } else if (characters[j] == ')') {
                    while (operators.peek() != '(') {
                        values.push(CalculatorBuilderInterface.calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.pop();
                } else if (CalculatorBuilderInterface.isOperator(characters[j])) {
                    while (!operators.empty() && !CalculatorBuilderInterface.isMorePriority((characters[j]), operators.peek())) {
                        values.push(CalculatorBuilderInterface.calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.push(characters[j]);
                }



            }
            while (!operators.empty()) {
                values.push(CalculatorBuilderInterface.calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
            }

            result.append(values.pop().toString());
            arrayStrings.add(String.valueOf(result));
            result.delete(0,result.length());


        }
        return this ;


    }


    @Override
    public Vector<String> build() {
        return arrayStrings;
    }

}
