import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

// Monday's tests, as a model for today's
class NumbersTest {
    @Test
    void handsOutEachNumberOnceThenMinusOne() {
        Numbers numbers = new Numbers(3);
        assertEquals(0, numbers.next());
        assertEquals(1, numbers.next());
        assertEquals(2, numbers.next());
        assertEquals(-1, numbers.next());
        assertEquals(-1, numbers.next());
    }

    @Test
    void aNegativeLimitIsRefused() {
        assertThrows(IllegalArgumentException.class, () -> new Numbers(-1));
    }

    @Test
    void fourCountersFindThePrimesBelow100000() throws InterruptedException {
        Numbers numbers = new Numbers(100_000);
        List<NumberCounter> counters = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            NumberCounter counter = new NumberCounter(numbers);
            counters.add(counter);
            threads.add(new Thread(counter));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        int primes = 0;
        int taken = 0;
        for (NumberCounter c : counters) {
            primes += c.getPrimes();
            taken += c.getTaken();
        }
        assertEquals(9592, primes);
        assertEquals(100_000, taken);
    }
}
