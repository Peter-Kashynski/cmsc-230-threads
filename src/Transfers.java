// two accounts, two threads, a dollar at a time in opposite directions
public class Transfers {
    // moves amount from one account to the other, holding both locks
    static void transfer(Account from, Account to, int amount) {
        String firstName = from.getName(); 
        String secondName = to.getName();
        int comesFirst = firstName.compareTo(secondName);

        if (comesFirst > 0) {
            String temp = firstName; 
            firstName = secondName; 
            secondName = temp;
        }
        synchronized (firstName) {
            synchronized (secondName) {
                from.add(-amount);
                to.add(amount);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Two accounts
        // Account alice = new Account("alice", 1000);
        // Account bob = new Account("bob", 1000);

        // Thread one = new Thread(() -> {
        //     for (int i = 0; i < 100_000; i++) {
        //         transfer(alice, bob, 1);
        //     }
        // });
        // Thread two = new Thread(() -> {
        //     for (int i = 0; i < 100_000; i++) {
        //         transfer(bob, alice, 1);
        //     }
        // });

        // one.start();
        // two.start();
        // one.join();
        // two.join();
        // System.out.println(alice.getBalance() + " + " + bob.getBalance() + " = "
        //         + (alice.getBalance() + bob.getBalance()));

        // Three accounts 
        Account alice = new Account("alice", 1000);
        Account bob = new Account("bob", 1000);
        Account carol = new Account("carol", 1000);

        Thread one = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                transfer(alice, bob, 1);
            }
        });
        Thread two = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                transfer(bob, carol, 1);
            }
        });
        Thread three = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                transfer(carol, alice, 1);
            }
        });

        one.start();
        two.start();
        three.start();

        one.join();
        two.join();
        three.join();

        System.out.println(alice.getBalance() + " + " + bob.getBalance() + " + " + 
                            carol.getBalance() + " = "
                + (alice.getBalance() + bob.getBalance() + carol.getBalance()));
        

        // sending to yourself 
        transfer(alice, alice, 1);
        System.out.println(alice.getBalance());
    }
}
