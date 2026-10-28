// counts the primes among the numbers it takes, until there are none left
public class NumberCounter implements Runnable {
    private final Numbers numbers;
    private int primes;
    private int taken;

    public NumberCounter(Numbers numbers) {
        this.numbers = numbers;
    }

    public void run() {
        int n = numbers.next();
        while (n != -1) {
            if (isPrime(n)) {
                primes++;
            }
            taken++;
            n = numbers.next();
        }
    }

    public int getPrimes() { return primes; }
    public int getTaken() { return taken; }

    // whether n is prime: no divisor from 2 up to its square root
    static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }
}
