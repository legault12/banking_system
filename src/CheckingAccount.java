public class CheckingAccount extends Account {
    private final double overdraftLimit;

    public CheckingAccount(String accountNumber, String customerName, double balance, double overdraftLimit) {
        super(accountNumber, customerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit >= amount)) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully. Remaining balance: $" + balance);
        } else {
            System.out.println("Invalid amount or overdraft limit exceeded.");
        }
    }
}
