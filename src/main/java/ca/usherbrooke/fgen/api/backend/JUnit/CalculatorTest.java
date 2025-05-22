package ca.usherbrooke.fgen.api.backend.JUnit;

import ca.usherbrooke.fgen.api.backend.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    /**
     * Object calculator utilisé pour les tests
     */
    Calculator calc = new Calculator();

    /**
     * Exécuté à la fin de chaque méthode de test (add, multiply, divide)
     */
    @AfterEach
    void tearDown() {
        calc = new Calculator();
    }

    /**
     * Tests de la méthode add
     */
    @Test
    void add() {
        assertEquals(5, calc.add(2, 3));
        assertEquals(0, calc.add(-2, 2));
        assertEquals(-5, calc.add(-2, -3));
    }

    /**
     * Tests de la méthode multiply
     */
    @Test
    void multiply() {
        assertEquals(6, calc.multiply(2, 3));
        assertEquals(0, calc.multiply(0, 5));
        assertEquals(-6, calc.multiply(-2, 3));
        assertEquals(6, calc.multiply(-2, -3));
    }

    /**
     * Tests de la méthode divide
     */
    @Test
    void divide() {
        assertEquals(2, calc.divide(6, 3));
        assertEquals(2.4, calc.divide(6, 2.5), 0.0001); // delta = tolérance d'erreur
    }

    /**
     * Tests limite de la méthode divide
     */
    @Test
    void divideByZero_throwsException() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }
}
