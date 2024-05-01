import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Bank {
    private final List<Account> accounts;
    private final Map<String, List<String>> closedAccountHistories;
    private final Map<String, Loan> loans = new HashMap<>();
    private static int loanCounter = 0;

    public Bank() {
        accounts = new ArrayList<>();
        closedAccountHistories = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public void closeAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getBalance() == 0) {
                closedAccountHistories.put(accountNumber, new ArrayList<>(account.getTransactionHistory()));
                accounts.remove(account);
                System.out.println("Account closed successfully.");
                return;
            }
        }
        System.out.println("Account not found or balance not zero. Cannot close account.");
    }

    public void depositToAccount(String accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.deposit(amount);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void withdrawFromAccount(String accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.withdraw(amount);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display.");
        } else {
            for (Account account : accounts) {
                account.displayAccountDetails();  // Display the basic account details first
                boolean hasLoan = false;
                for (Loan loan : loans.values()) {  // Check all loans to see if the current account has an associated loan
                    if (loan.getBorrowerAccountNumber().equals(account.getAccountNumber())) {
                        if (!hasLoan) {  // Check to ensure header is only printed once
                            System.out.println("   Associated Loans:");
                            hasLoan = true;
                        }
                        System.out.println("     Loan ID: " + loan.getLoanId() + ", Amount: $" + loan.getLoanAmount() +
                                ", Interest Rate: " + loan.getInterestRate() + "%, Monthly Payment: $" + loan.getMonthlyPayment());
                    }
                }
                if (!hasLoan) {
                    System.out.println("   No associated loans.");
                }
            }
        }
    }

    public void printTransactionHistory(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.printTransactionHistory();
                return;
            }
        }
        if (closedAccountHistories.containsKey(accountNumber)) {
            System.out.println("Transaction history for closed Account " + accountNumber + ":");
            for (String transaction : closedAccountHistories.get(accountNumber)) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("No account found with account number: " + accountNumber);
        }
    }

    public void updateInterestRate(String accountNumber, double newInterestRate) {
        for (Account account : accounts) {
            if (account instanceof SavingsAccount && account.getAccountNumber().equals(accountNumber)) {
                ((SavingsAccount) account).setInterestRate(newInterestRate);
                return;
            }
        }
        System.out.println("No savings account found with account number: " + accountNumber);
    }

    public String generateLoanId() {
        return "LN-" + (++loanCounter) + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public void applyForLoan(String accountNumber, double amount, double rate, int period) {
        String loanId = generateLoanId();
        if (accounts.stream().anyMatch(a -> a.getAccountNumber().equals(accountNumber))) {
            Loan loan = new Loan(loanId, accountNumber, amount, rate, period);
            loans.put(loanId, loan);
            System.out.println("Loan applied successfully. Loan ID: " + loanId);
        } else {
            System.out.println("Account number not found. Loan application failed.");
        }
    }

    public void makeLoanPayment(String loanId, double amount) {
        Loan loan = loans.get(loanId);
        if (loan != null) {
            loan.makePayment(amount);
        } else {
            System.out.println("Loan ID not found. Payment failed.");
        }
    }

    public void displayLoanDetails(String loanId) {
        Loan loan = loans.get(loanId);
        if (loan != null) {
            System.out.println(loan.getLoanDetails());
        } else {
            System.out.println("Loan ID not found.");
        }
    }
}
