package whatever;

import java.lang.reflect.Method;

/**
 * Simple Calculator
 */
public class Calculator {

    public Calculator(boolean isAdvanced) {
        this.isAdvanced = isAdvanced;
    }

    private boolean isAdvanced = false;

    int sum(int x, int y) {
        return x + y;
    }

    double sum(double x, double y) {
        return x + y;
    }

    int divide(int x, int y) {
        return x / y;
    }

    double divide(double x, double y) {
        return x / y;
    }

    int multiply(int x, int y) {
        return x * y;
    }

    double multiply(double x, double y) {
        return x * y;
    }

    int subtract(int x, int y) {
        return x - y;
    }

    double subtract(double x, double y) {
        return x - y;
    }

    /**
     * Computes the result of a function
     * @param terms coefficient and exponent pairs
     * @return result
     */
    double computeFunction(Term... terms) throws IllegalAccessException {
        if (!isAdvanced) {
            throw new IllegalAccessException("Not supported for a basic calculator");
        }

        double total = 0;
        for (Term term : terms) {
            total = sum(total, term.compute());
        }
        return total;
    }

    static class Term {
        double coefficient;
        double variable;
        double exponent;

        public Term(double coefficient, double variable, double exponent) {
            this.coefficient = coefficient;
            this.variable = variable;
            this.exponent = exponent;
        }

        double compute() {
            return coefficient * Math.pow(variable, exponent);
        }
    }
}
