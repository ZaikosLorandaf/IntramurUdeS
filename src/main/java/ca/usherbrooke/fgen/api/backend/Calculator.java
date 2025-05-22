package ca.usherbrooke.fgen.api.backend;

import java.util.stream.DoubleStream;

/**
 * Classe pour éxécuter des actions mathématiques
 * Utils pour la preuve de concept des tests
 */
public class Calculator {

    double add(double... operands) {
        return DoubleStream.of(operands)
                .sum();
    }

    double multiply(double... operands) {
        return DoubleStream.of(operands)
                .reduce(1, (a, b) -> a * b);
    }

    double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }
}