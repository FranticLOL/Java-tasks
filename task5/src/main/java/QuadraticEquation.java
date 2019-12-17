import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static java.lang.Double.valueOf;

public class QuadraticEquation implements Callable<String> {
    private Double a;
    private Double b;
    private Double c;

    public QuadraticEquation(String inputString) {
        String[] numbersInCurrentString = inputString.split(" ");
        if (numbersInCurrentString.length < 3) {
            throw new IllegalArgumentException();
        }
        a = valueOf(numbersInCurrentString[0]);
        b = valueOf(numbersInCurrentString[1]);
        c = valueOf(numbersInCurrentString[2]);
    }

    public List<Double> getAnswer() {
        List<Double> solutions = new ArrayList<>();
        if (a == 0. && b == 0. && c != 0) {
            return solutions;
        }
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            solutions.add((-b + Math.sqrt(discriminant)) / (2 * a));
            solutions.add((-b - Math.sqrt(discriminant)) / (2 * a));
        } else if (discriminant == 0) {
            solutions.add(-b / (2 * a));
        }
        return solutions;
    }

    public String stringOfSolutions() {
        StringBuilder result = new StringBuilder();
        List<Double> solutions = getAnswer();
        if (solutions.size() == 0) {
            return "No solutions";
        }
        for (int counter = 0; counter < solutions.size(); ++counter) {
            result.append(" x").append((counter + 1)).append(" = ").append(solutions.get(counter));
        }
        return result.toString();
    }

    @Override
    public String call() {
        return "(" + a + ")x^2 + " + "(" + b + ")x + " + c + " = 0 :" + stringOfSolutions();
    }
}
