package com.example.ppfx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class RegexCalculatorBuilderTest {
    RegexCalculatorBuilder regexCalculatorBuilder = RegexCalculatorBuilder.getInstance();
    @Test
    void test1()
    {
        Vector<String> expressions = new Vector<>();
        expressions.add("2+2");
        expressions.add("4 + 3 * 4");
        expressions.add("3 + 2 - 7");
        expressions.add("4 * ( 5 - 6 ) / 3");
        expressions.add("0.56 + 0.2 * 4");
        expressions.add("3 + ( 16 - 6 ) * 13");


        Vector<String> answers = regexCalculatorBuilder.calculate(expressions).build();
        Vector<String> expected = new Vector<>();
        expected.add("4.0");
        expected.add("16.0");
        expected.add("-2.0");
        expected.add(Double.toString((double) -4 /3));
        expected.add("1.36");
        expected.add("133.0");



        Assertions.assertEquals(answers,expected);
    }

}