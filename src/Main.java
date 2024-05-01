import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        // Load accounts from file
        try {
            File accountFile = new File("src/accounts.csv");
            Scanner fileScanner = new Scanner(accountFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(",");
                String type = details[0];
                String accountNumber = details[1];
                String customerName = details[2];
                double balance = Double.parseDouble(details[3]);
                if (type.equals("SavingsAccount")) {
                    double interestRate = Double.parseDouble(details[4]);
                    bank.addAccount(new SavingsAccount(accountNumber, customerName, balance, interestRate));
                } else if (type.equals("CheckingAccount")) {
                    double overdraft = Double.parseDouble(details[4]);
                    bank.addAccount(new CheckingAccount(accountNumber, customerName, balance, overdraft));
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        boolean quit = false;
        while (!quit) {
            System.out.println("\nWelcome to the Simple Banking System");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Add Interest to All Savings Accounts");
            System.out.println("5. Close Account");
            System.out.println("6. Display Transaction History");
            System.out.println("7. Update Interest Rate");
            System.out.println("8. Apply for Loan");
            System.out.println("9. Make Loan Payment");
            System.out.println("10. Display Loan Details");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    bank.displayAccounts();
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String accNumDeposit = scanner.next();
                    System.out.print("Enter deposit amount: ");
                    double amountDeposit = scanner.nextDouble();
                    bank.depositToAccount(accNumDeposit, amountDeposit);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String accNumWithdraw = scanner.next();
                    System.out.print("Enter withdrawal amount: ");
                    double amountWithdraw = scanner.nextDouble();
                    bank.withdrawFromAccount(accNumWithdraw, amountWithdraw);
                    break;
                case 4:
                    bank.getAccounts().stream()
                            .filter(account -> account instanceof SavingsAccount)
                            .forEach(account -> ((SavingsAccount) account).addInterest());
                    System.out.println("Interest has been added to all savings accounts.");
                    break;
                case 5:
                    System.out.print("Enter the account number of the account you wish to close: ");
                    String accNumClose = scanner.next();
                    bank.closeAccount(accNumClose);
                    break;
                case 6:
                    System.out.print("Enter account number: ");
                    String accNumHistory = scanner.next();
                    bank.printTransactionHistory(accNumHistory);
                    break;
                case 7:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter new interest rate (%): ");
                    double newInterestRate = scanner.nextDouble();
                    bank.updateInterestRate(accountNumber, newInterestRate);
                    break;
                case 8:
                    System.out.print("Enter account number: ");
                    String loanAccountNumber = scanner.next();
                    System.out.print("Loan Amount: ");
                    double loanAmount = scanner.nextDouble();
                    System.out.print("Interest Rate (%): ");
                    double loanInterestRate = scanner.nextDouble();
                    System.out.print("Repayment Period (months): ");
                    int loanRepaymentPeriod = scanner.nextInt();
                    bank.applyForLoan(loanAccountNumber, loanAmount, loanInterestRate, loanRepaymentPeriod);
                    break;
                case 9:
                    System.out.print("Enter Loan ID: ");
                    String payLoanId = scanner.next();
                    System.out.print("Payment Amount: ");
                    double paymentAmount = scanner.nextDouble();
                    bank.makeLoanPayment(payLoanId, paymentAmount);
                    break;
                case 10:
                    System.out.print("Enter Loan ID to display details: ");
                    String detailLoanId = scanner.next();
                    bank.displayLoanDetails(detailLoanId);
                    break;
                case 11:
                    System.out.println("Exiting...");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
