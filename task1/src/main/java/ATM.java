import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ATM {
    protected List<List<Integer>> exchange(int value, int[] banknotes) { //basic algorithm
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(banknotes);
        for (int i = banknotes.length - 1; i > -1; --i) {
            if (value - banknotes[i] < 0) {
                continue;
            }
            if (value - banknotes[i] == 0) {
                List<Integer> buffer = new ArrayList<>();
                buffer.add(banknotes[i]);
                result.add(buffer);
            }
            if (value - banknotes[i] > 0) {
                for (List<Integer> buffer : exchange(value - banknotes[i], Arrays.copyOfRange(banknotes, 0, i + 1))) {
                    buffer.add(banknotes[i]);
                    result.add(buffer);
                }
            }
        }

        return result;
    }

    void work() throws ATMExeption, IOException {
        List<Integer> banknotes = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Input a banknotes: ");

        Pattern pattern = Pattern.compile("\\s*\\s");
        String[] banknotesStr = pattern.split(input.nextLine());

        for (String note : banknotesStr) {
            if (note.matches("[0-9]+")) {
                if (Integer.parseInt(note) > 0) {
                    banknotes.add(Integer.parseInt(note));
                } else {
                    throw new ATMExeption("You cannot enter not positive banknote: ", Integer.parseInt(note));
                }
            } else {
                throw new IOException("Incorrect input of banknotes");
            }
        }

        System.out.print("Input output value: ");
        String value = input.nextLine();

        if (!value.matches("[0-9]+")) {
            throw new IOException("Incorrect input of value");
        }
        if (Integer.parseInt(value) <= 0) {
            throw new ATMExeption("You cannot enter not positive value: ", Integer.parseInt(value));
        }

        banknotes = removeDuplicates(banknotes);

        int[] banknotesArr = banknotes.stream().mapToInt(i -> i).toArray();
        List<List<Integer>> result = exchange(Integer.parseInt(value), banknotesArr);
        System.out.println("Number of options: " + result.size());

        for (List<Integer> integers : result) {
            for (Integer number : integers) {
                if (number <= 0) {
                    throw new ATMExeption("Result error", number);
                }
            }
        }
        result.forEach(System.out::println);
    }

    private List<Integer> removeDuplicates(List<Integer> banknotes) {
        Set set = new LinkedHashSet();
        set.addAll(banknotes);
        return new ArrayList(set);
    }
}


class ATMExeption extends Exception {
    ATMExeption(String message, int banknote) {
        super(message + banknote);
    }
}