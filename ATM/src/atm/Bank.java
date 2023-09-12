package atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction{
	String type;
	double amount;
	
	public Transaction(String type, double amount) {
		this.type = type;
		this.amount = amount;
		
	}
	public String toString() {
		return type + " : " + amount;
		
	}
}
class BankAccount{
	
	private long AccountNumber;
	double balance;
	private List <Transaction> transactionHistory;
	public BankAccount(long AccountNumber) {
		this.AccountNumber = AccountNumber;
		this.balance= 0.0;
		this.transactionHistory = new ArrayList<>();
	}
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amount) {
		if(amount > 0) {
			balance += amount;
			transactionHistory.add(new Transaction("Deposited", amount));
		}
		System.out.println("Cuurent Balance: " + balance);
	}
	public void transfer(double amount, BankAccount receiver) {
		if(amount >0 && balance >= amount) {
			balance -= amount;
			receiver.deposit(amount);
			transactionHistory.add(new Transaction("Transfered to"+ receiver.AccountNumber,amount));
		}
		
		System.out.println("Cuurent Balance: " + balance);
	}
	public void withdraw(double amount) {
		if(amount >0 && amount <= balance) {
			balance -= amount;
			
			transactionHistory.add(new Transaction("Withdrawed",amount));
		}
		
		System.out.println("Cuurent Balance: " + balance);
	}
	public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
	
}

public class Bank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Your Accouunt No.");
		double balance = 0;
		long no = sc.nextLong();
        BankAccount account1 = new BankAccount(no);
        System.out.println("Welcome: "+account1);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            System.out.print("Enter Your Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Deposit Amount: $");
                    double depositAmount = sc.nextDouble();
                    account1.deposit(depositAmount);
                    balance += depositAmount;
                    System.out.println("Deposited $" + depositAmount + " into " + account1 + "." );
                    break;
                case 2:
                    System.out.print("Enter Withdrawal Amount: $");
                    double withdrawalAmount = sc.nextDouble();
                    if (balance >= withdrawalAmount) {
	                    account1.withdraw(withdrawalAmount);
	                    System.out.println("Withdrawn $" + withdrawalAmount + " from "+ account1+".");
	                    balance -= withdrawalAmount;
                    }
                    else {
                    	System.out.println("You Have less than" + withdrawalAmount + " amount" );
                    }
                    break;
                case 3:
                	System.out.print("Enter Reciepent Account No.: ");
                	long ac2 = sc.nextLong();
                    System.out.print("Enter Transfer Amount: $");
                    double transferAmount = sc.nextDouble();
                    BankAccount account2 = new BankAccount(ac2);
                    if (balance >= transferAmount) {
	                    account1.transfer(transferAmount, account2);
	                    System.out.println("Transferred $" + transferAmount + " from "+ account1 + " to " + account2 +".");
	                    balance -= transferAmount;
                    }
                    else {
                    	System.out.println("You Have less than" + transferAmount + " amount" );
                    }
                    
                    break;
                case 4:
                    List<Transaction> history = account1.getTransactionHistory();
                    System.out.println("\nTransaction History For " + account1 + ":");
                    for (Transaction transaction : history) {
                        System.out.println(transaction);
                    }
                    break;
                case 5:
                    System.out.println("Exiting the bank.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

	}

	
	
}
