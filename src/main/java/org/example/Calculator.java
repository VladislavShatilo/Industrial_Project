package org.example;

import java.util.Stack;
import java.util.Vector;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

    public class Calculator {

        public Vector<String> calculateSimple(Vector<String> strings) {
            Stack<Double> values = new Stack<>();
            Stack<Character> operators = new Stack<>();
            StringBuilder result= new StringBuilder();
            Vector<String> arrayStrings = new Vector<>();



            for (String string : strings) {
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
                            values.push(calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
                        }
                        operators.pop();
                    } else if (isOperator(characters[j])) {
                        while (!operators.empty() && !isMorePriority((characters[j]), operators.peek())) {
                            values.push(calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
                        }
                        operators.push(characters[j]);
                    }



                }
                while (!operators.empty()) {
                    values.push(calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
                }

                result.append(values.pop().toString());
                arrayStrings.add(String.valueOf(result));
                result.delete(0,result.length());


            }
            return arrayStrings;


        }

        public Vector<String> calculateWithRegex(Vector<String> strings){
            Stack<Double> values = new Stack<>();
            Stack<Character> operators = new Stack<>();
            StringBuilder result= new StringBuilder();
            Vector<String> arrayStrings = new Vector<>();



            for (String string : strings) {
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
                            values.push(calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
                        }
                        operators.pop();
                    } else if (isOperator(characters[j])) {
                        while (!operators.empty() && !isMorePriority((characters[j]), operators.peek())) {
                            values.push(calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
                        }
                        operators.push(characters[j]);
                    }



                }
                while (!operators.empty()) {
                    values.push(calculateSimpleExp(operators.pop(), values.pop(), values.pop()));
                }

                result.append(values.pop().toString());
                arrayStrings.add(String.valueOf(result));
                result.delete(0,result.length());


            }
            return arrayStrings;
        }
        public Vector<String> calculateLibrary(Vector<String> strings)
        {
            Vector<String> result = new Vector<>();
            for(String expStr : strings)
            {
                Expression exp = new ExpressionBuilder(expStr).build();
                result.add(String.valueOf(exp.evaluate()));
            }

            return result;

        }

        private static boolean isOperator(char operator) {
            return operator == '+' || operator == '*' || operator == '/' || operator == '-';
        }

        private static int getPriority(char operator) {
            if (operator == '+' || operator == '-') {
                return 1;
            } else if (operator == '*' || operator == '/') {
                return 2;
            } else {
                return 0;
            }
        }

        private static double calculateSimpleExp(char operator, double secondOp, double firstOp)
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


        private static boolean isMorePriority(char operator1,char operator2)
        {
            int prior1 = getPriority(operator1);
            int prior2 = getPriority(operator2);
            return prior1 >= prior2;
        }

    }

