import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        try {
            atm.work();
        } catch (ATMExeption | IOException exeption) {
            System.out.println(exeption.getMessage());
        }
    }
}