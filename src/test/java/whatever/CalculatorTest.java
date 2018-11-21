package whatever;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import whatever.Calculator.Term;

class CalculatorTest {

    Calculator calculator;

    @Nested
    @DisplayName("Basic Calculator Tests")
    class BasicCalculatorTest {

        @BeforeEach
        void setUp() {
            calculator = new Calculator(false);
        }

        @Nested
        @DisplayName("Sum Tests")
        class SumTest {

            @ParameterizedTest(name = "{0} + {1} = {2}")
            @CsvSource({"1,1,2","-1,-1,-2"})
            @DisplayName("sum cases")
            void sum(int x, int y, int expectedResult) {
                int result = calculator.sum(x, y);
                assertEquals(expectedResult, result);
            }
        }

        @Nested
        @DisplayName("Subtract Tests")
        class SubtractTest {

            @ParameterizedTest(name = "{0} - {1} = {2}")
            @CsvSource({"1,1,0","-1,-1,0"})
            @DisplayName("subtract cases")
            void subtract(int x, int y, int expectedResult) {
                int result = calculator.subtract(x, y);
                assertEquals(expectedResult, result);
            }
        }

        @Nested
        @DisplayName("Multiply Tests")
        class MultiplyTest {

            @ParameterizedTest(name = "{0} * {1} = {2}")
            @CsvSource({"1,1,1","-1,-1,1"})
            @DisplayName("multiply cases")
            void multiply(int x, int y, int expectedResult) {
                int result = calculator.multiply(x, y);
                assertEquals(expectedResult, result);
            }
        }

        @Nested
        @DisplayName("Divide Tests")
        class DivideTest {

            @ParameterizedTest(name = "{0} / {1} = {2}")
            @CsvSource({"1,1,1","-1,-1,1"})
            @DisplayName("divide cases")
            void divide(int x, int y, int expectedResult) {
                int result = calculator.divide(x, y);
                assertEquals(expectedResult, result);
            }
        }
    }

    @Nested
    @DisplayName("Advanced Calculator Tests")
    class AdvancedCalculatorTest {

        @BeforeEach
        void setUp() {
            calculator = new Calculator(true);
        }

        @Nested
        @DisplayName("Function Compute Tests")
        class FunctionComputeTest {

            @ParameterizedTest
            @ValueSource(strings = {
                    "1,2,3:4,5,1=28"
            })
            @DisplayName("function cases")
            void functionCompute(String functionString) throws Exception {
                double expectedResult = Double.parseDouble(functionString.split("=")[1]);
                String equation = functionString.split("=")[0];
                List<Term> function = parseTerms(equation);

                double result = calculator.computeFunction(function.toArray(new Term[function.size()]));

                assertEquals(expectedResult, result);
            }

            private List<Term> parseTerms(String function) {
                List<Term> terms = new ArrayList<>();

                String[] termStrings = function.split(":");
                for (String termString : termStrings) {
                    String[] termParts = termString.split(",");
                    double coefficient = Double.parseDouble(termParts[0]);
                    double variable = Double.parseDouble(termParts[1]);
                    double exponent = Double.parseDouble(termParts[2]);
                    Term term = new Term(coefficient, variable, exponent);
                    terms.add(term);
                }
                return terms;
            }
        }
    }

}