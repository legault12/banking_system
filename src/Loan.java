public class Loan {
    private final String loanId;
    private final String borrowerAccountNumber;
    private double loanAmount;
    private double interestRate;  // Annual interest rate
    private int repaymentPeriod;  // In months
    private double monthlyPayment;

    public Loan(String loanId, String borrowerAccountNumber, double loanAmount, double interestRate, int repaymentPeriod) {
        this.loanId = loanId;
        this.borrowerAccountNumber = borrowerAccountNumber;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.repaymentPeriod = repaymentPeriod;
        this.monthlyPayment = calculateMonthlyPayment();
    }

    private double calculateMonthlyPayment() {
        double monthlyInterestRate = interestRate / 1200;  // Convert annual rate percentage to a decimal and divide by 12
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -repaymentPeriod));
    }

    public void makePayment(double amount) {
        if (amount > loanAmount) {
            System.out.println("Payment exceeds the remaining loan balance. Paying off the loan.");
            loanAmount = 0;
        } else {
            loanAmount -= amount;
        }

        if (loanAmount > 0) {
            System.out.println("Payment applied. Remaining balance: $" + String.format("%.2f", loanAmount));
        } else {
            System.out.println("Loan fully paid off.");
        }
        // Recalculate the monthly payment after a payment is made in case of changes in the balance affecting the schedule
        this.monthlyPayment = calculateMonthlyPayment();
    }

    public void updateLoan(double newInterestRate, int newRepaymentPeriod) {
        this.interestRate = newInterestRate;
        this.repaymentPeriod = newRepaymentPeriod;
        this.monthlyPayment = calculateMonthlyPayment();  // Recalculate the monthly payment if terms change
        System.out.println("Loan terms updated. New monthly payment: $" + String.format("%.2f", monthlyPayment));
    }

    public String getLoanDetails() {
        return "Loan ID: " + loanId +
                "\nAccount Number: " + borrowerAccountNumber +
                "\nLoan Amount: $" + String.format("%.2f", loanAmount) +
                "\nInterest Rate: " + String.format("%.2f", interestRate) + "%" +
                "\nRepayment Period: " + repaymentPeriod + " months" +
                "\nMonthly Payment: $" + String.format("%.2f", monthlyPayment);
    }

    public String getLoanId() {
        return loanId;
    }

    public String getBorrowerAccountNumber() {
        return borrowerAccountNumber;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }
}
