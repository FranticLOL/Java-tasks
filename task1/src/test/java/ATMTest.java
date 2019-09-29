import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ATMTest {

    @Test
    public void payMoneyFirst() {
        ATM atm = new ATM();
        List<List<Integer>> expected = new ArrayList();
        List<Integer> option1 = Arrays.asList(2, 3);
        expected.add(option1);

        List<List<Integer>> result = atm.exchange(5, new int[]{3, 2});

        Assert.assertEquals(expected, result);

    }

    @Test
    public void payMoneySecond() {
        ATM atm = new ATM();
        List<List<Integer>> expected = new ArrayList();
        List<Integer> option1 = Arrays.asList(1, 2, 2);
        List<Integer> option2 = Arrays.asList(1, 1, 1, 2);
        List<Integer> option3 = Arrays.asList(1, 1, 1, 1, 1);

        expected.add(option1);
        expected.add(option2);
        expected.add(option3);

        List<List<Integer>> result = atm.exchange(5, new int[]{2, 1});

        Assert.assertEquals(expected, result);
    }

    @Test
    public void payMoneyThird() {
        ATM atm = new ATM();
        List<List<Integer>> expected = new ArrayList();
        List<Integer> option1 = Arrays.asList(7);
        List<Integer> option2 = Arrays.asList(2, 2, 3);

        expected.add(option1);
        expected.add(option2);

        List<List<Integer>> result = atm.exchange(7, new int[]{7, 3, 2});

        Assert.assertEquals(expected, result);
    }

    @Test
    public void payMoneyForth() {
        ATM atm = new ATM();
        List<List<Integer>> expected = new ArrayList();

        List<List<Integer>> result = atm.exchange(7, new int[]{5, 4});

        Assert.assertEquals(expected.size(), result.size());
    }

    @Test
    public void payMoneyFifth() {
        ATM atm = new ATM();
        List<List<Integer>> expected = new ArrayList();

        List<List<Integer>> result = atm.exchange(0, new int[]{5, 4});

        Assert.assertEquals(expected.size(), result.size());
    }

    @Test(expected = ATMExeption.class)
    public void workFirst() throws IOException, ATMExeption {
        ATM atm = new ATM();

        atm.work();
    }
}