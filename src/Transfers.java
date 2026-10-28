// two accounts, two threads, a dollar at a time in opposite directions
public class Transfers {
    // moves amount from one account to the other, holding both locks
    static void transfer(Account from, Account to, int amount) {
        synchronized (from) {
            synchronized (to) {
                from.add(-amount);
                to.add(amount);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account alice = new Account("alice", 1000);
        Account bob = new Account("bob", 1000);

        Thread one = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                transfer(alice, bob, 1);
            }
        });
        Thread two = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                transfer(bob, alice, 1);
            }
        });

        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println(alice.getBalance() + " + " + bob.getBalance() + " = "
                + (alice.getBalance() + bob.getBalance()));
    }
}
