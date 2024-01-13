package com.example.ppfx;

import java.util.Vector;

public interface CalculatorBuilderInterface {
    CalculatorBuilderInterface calculate(Vector<String> exp);



    Vector<String> build();
    static boolean isOperator(char operator) {
        return operator == '+' || operator == '*' || operator == '/' || operator == '-';
    }

     static int getPriority(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else {
            return 0;
        }
    }

    static double calculateSimpleExp(char operator, double secondOp, double firstOp)
    {
        return switch (operator) {
            case '+' -> firstOp + secondOp;
            case '-' -> firstOp - secondOp;
            case '*' -> firstOp * secondOp;
            case '/' -> {
                if (secondOp == 0) {
                    throw new UnsupportedOperationException("Divided by zero");
                }
                yield firstOp / secondOp;
            }
            default -> 0;
        };

    }


    static boolean isMorePriority(char operator1, char operator2)
    {
        int prior1 = getPriority(operator1);
        int prior2 = getPriority(operator2);
        return prior1 < prior2;
    }


}
