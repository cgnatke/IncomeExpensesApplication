import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.UnsupportedOperationException;

public class IncomeExpensesApplication {

	//private static final String FILENAME = "./account.csv";

	public static void main(String[] args) throws FileNotFoundException, IOException {
	
		double[] expenses = new double[9];
		String fileName = "./data/account_example.csv"; //+ promptForFilename();
		readInputFile(expenses, fileName);
		System.out.println("Done with income/expenses input");
		printReport(expenses);
	}

	private static void readInputFile(double[] expenses, String fileName) throws FileNotFoundException, IOException {

		BufferedReader br = null;
		FileReader fr = null;

		fr = new FileReader(fileName);
		br = new BufferedReader(fr);

		String sCurrentLine;

		br = new BufferedReader(new FileReader(fileName));
		
		//start at line 4
		br.readLine();
		br.readLine();
		br.readLine();
		while ((sCurrentLine = br.readLine()) != null) {
			processTransaction(sCurrentLine, expenses);
			//System.out.println(sCurrentLine);
		}

		if (br != null) {
			br.close();
		}
		if (fr != null) {
			fr.close();			
		}
	}


	private static void processTransaction(String transaction, double[] expenses) {
		boolean inputValid = false;		
		while (!inputValid) {
			//todo should use double... Use a type designed for currency instead...
			double amount = Double.parseDouble(transaction.split(",")[1].replace("\"","")); //get rid of double quotes	
			String description = transaction.split(",")[2].replace("\"","");
			String date = transaction.split(",")[0].replace("\"","");

			System.out.println("What type of transaction is this?: ");
			System.out.println("------------------------");
			System.out.println("Paycheck: 0");
			System.out.println("Cellphone Allowance: 1");
			System.out.println("Rent Exp: 2");
			System.out.println("Apartment Exp: 3");
			System.out.println("Cell Exp: 4");
			System.out.println("Fuel Exp: 5");
			System.out.println("Food Exp: 6");
			System.out.println("Out to Dinner Exp: 7");
			System.out.println("Misc Exp: 8");
			System.out.println("------------------------");

			System.out.println("Description: " + description);
			System.out.println("Amount: " + amount);
			System.out.println("Date: " + date);
			System.out.println();

			try {	
				int selection = Integer.parseInt(System.console().readLine());		
				expenses[selection] += amount;
				//storeSelection(selection, description);
				inputValid = true;
				System.out.println();			
			} catch (Exception e) {

				System.out.println("Your input was not valid. Try again!\n");
			}
		}
	}

	private static void printReport(double [] expenses) {
		System.out.println("Paycheck: " + expenses[0]);
		System.out.println("Cellphone Allowance: " + expenses[1]);
		System.out.println("Rent Exp: " + expenses[2]);
		System.out.println("Apartment Exp: " + expenses[3]);
		System.out.println("Cell Exp: " + expenses[4]);
		System.out.println("Fuel Exp: " + expenses[5]);
		System.out.println("Food Exp: " + expenses[6]);
		System.out.println("Out to Dinner Exp: " + expenses[7]);
		System.out.println("Misc Exp: " + expenses[8]);
		System.out.println();
		System.out.println("Total Income: " + expenses[0]);

		double sumExpenses = expenses[1] + expenses[2] + expenses[3] + expenses[4] + 
			expenses[5] + expenses[6] + expenses[7] + expenses[8];

		System.out.println("Total Expenses: " + sumExpenses);
		System.out.println("-------------------------------");
		System.out.println("Net income: " + (expenses[0] + sumExpenses));
		System.out.println();
	}

	private static void storeSelection(int selection, String description) {
		//implement database insert here...
		throw new UnsupportedOperationException("TODO: this method hasn't been implmented yet!");

	}

	private static String promptForFilename() {
		System.out.print("Input filename?: ");
		//being lazy by not writing error handling or input validation
		return System.console().readLine();			
	}

}


/* record example
04/07/2017,"-13.07","TOPS MARKETS #3 CAZENOVIA NY USA",
*/

/* 
Income:
	Paycheck	
	Cellphone allowance


Expenses:

	Rent
	Apartment Expenses
	Cell phone bill
	Gas for Car
	Food	
	OutToDinner
	Misc

*/



