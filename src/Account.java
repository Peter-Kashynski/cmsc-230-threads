// an account: a name and a balance in whole dollars
public class Account {
    private final String name;
    private int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void add(int amount) {
        balance = balance + amount;
    }
}
