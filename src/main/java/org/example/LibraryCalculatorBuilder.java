package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Vector;

public class LibraryCalculatorBuilder implements CalculatorBuilderInterface{
   private final Vector<String> result = new Vector<>();
    @Override
    public CalculatorBuilderInterface calculate(Vector<String> expr) {

        for(String expStr : expr)
        {
            Expression exp = new ExpressionBuilder(expStr).build();
            result.add(String.valueOf(exp.evaluate()));
        }

        return this;
    }

    @Override
    public Vector<String> build() {
        return result;
    }
}
