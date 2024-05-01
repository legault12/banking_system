public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String customerName, double balance, double interestRate) {
        super(accountNumber, customerName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Interest Rate: " + interestRate + "%");
    }

    public void addInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        transactionHistory.add("Interest added: $" + interest + " | New balance: $" + balance);
        System.out.println("Interest added: $" + interest);
    }

    public void setInterestRate(double newInterestRate) {
        this.interestRate = newInterestRate;
        System.out.println("New interest rate set: " + newInterestRate + "%");
    }
}
