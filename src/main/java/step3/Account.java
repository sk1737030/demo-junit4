package step3;

import javax.naming.InsufficientResourcesException;

public class Account {

    private String name;
    private int balance;

    public Account(String name) {
        this.name = name;
        this.balance = 0;
    }

    public void deposit(int i) {
        balance += i;
    }

    public boolean hasPositiveBalance() {
        return balance > 0;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int i) throws InsufficientResourcesException {
        if (balance - i < 0) {
            throw new InsufficientResourcesException("Not enough Balance");
        }

        balance -= i;
    }
}
