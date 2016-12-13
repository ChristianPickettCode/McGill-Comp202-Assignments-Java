public class YearlyBudget {

	public static void main(String[] args) {

		double preTaxIncome = Double.parseDouble(args[0]);
		double creditCardBalance = Double.parseDouble(args[1]);
		double annualInterestRate = Double.parseDouble(args[2]);
		long creditCardNumber = Long.parseLong(args[3]);
		double monthlyRent = Double.parseDouble(args[4]);

		double postTaxIncome = preTaxIncome - calculateTaxes(preTaxIncome, 10000, 20, 20000, 30, 45000, 50);

		double totalSavings = 0;

		// Validate Credit Card 
		if (validateCreditCard(creditCardNumber)) {
			System.out.println("-------------------------------");

			// Display monthly credit card balance
			printBalance(creditCardBalance, annualInterestRate, buildExpenses(monthlyRent), buildPayments(postTaxIncome));

			System.out.println("-------------------------------");

			// Display Monthly Savings
			for (int i = 0; i < 12; i++) {

				System.out.println("Monthly " + (i + 1) + " Savings: " + monthlySavings(buildExpenses(monthlyRent)[i], buildPayments(postTaxIncome)[i]));
				
				// Total Savings 
				totalSavings += monthlySavings(buildExpenses(monthlyRent)[i], buildPayments(postTaxIncome)[i]);
				
			}

			System.out.println("-------------------------------");

			System.out.println("Total Yearly Savings: " + totalSavings);

			System.out.println("-------------------------------");


		} else {

			System.out.println("Invalid card");
		}
		
		
	}

	// Method to calculate taxes based on different tax brackets
	public static double calculateTaxes (double yearlyIncome, double bracket1Dollars, double bracket1Rate, double bracket2Dollars, double bracket2Rate, double bracket3Dollars, double bracket3Rate) {
		
		double taxBetweenBracket1AndBracket2 = 0;
		double taxBetweenBracket2AndBracket3 = 0;
		double taxBetweenBracket3AndRestOfIncome = 0;

		if (yearlyIncome >= bracket1Dollars) {
			// Tax for the first tax bracket
			taxBetweenBracket1AndBracket2 = (bracket2Dollars - bracket1Dollars) * (bracket1Rate / 100);

		}

		if (yearlyIncome >= bracket2Dollars) {
			// Tax for the second tax bracket
			taxBetweenBracket2AndBracket3 = (bracket3Dollars - bracket2Dollars) * (bracket2Rate / 100);

		}
		if (yearlyIncome >= bracket3Dollars) {
			// Tax for the rest of the income
			taxBetweenBracket3AndRestOfIncome = (yearlyIncome - bracket3Dollars) * (bracket3Rate / 100);

		} 


		// Total taxed amount based on the specifed  income
		double tax = taxBetweenBracket1AndBracket2 + taxBetweenBracket2AndBracket3 + taxBetweenBracket3AndRestOfIncome;

		//System.out.println(tax);
		return tax;
	}

	// Method to calculate the monthly saveings
	public static double monthlySavings (double totalMonthlyExpenses, double monthlyIncomeAfterTax) {

		// Income minus expenses
		return monthlyIncomeAfterTax - totalMonthlyExpenses;
	}

	// Method to validate the credit card number
	public static boolean validateCreditCard (long creditCardNumber) {
	
		// Sum and credit card number variables
		int sumOfOdds = 0;
		int sumOfEvens = 0;
		int checkSum = 0;
		long creditCardNumberForOdds = creditCardNumber;
		long creditCardNumberForEvens = creditCardNumber;

		// for loop to get the sum of odd values in credit card number
		for (int i = 0; i < 8; i++) {

			// Add the first digit from the right to sumOfOdds
			sumOfOdds += creditCardNumberForOdds % 10;

			// Divide by 100 to move the decimal place to get access to the next odd number location
			creditCardNumberForOdds = creditCardNumberForOdds / 100;
	
		}

		// for loop to get the sum of 2 times the remainder of the values in the even locations 
		for (int i = 0; i < 8; i++) {
			
			// Divide by 10 to get access the the initial even number location
			creditCardNumberForEvens = creditCardNumberForEvens / 10;

			// Add 2 times the remainder of the value in the even location 
			sumOfEvens += (2 * (creditCardNumberForEvens % 10)) % 9;

			// Move to the next location by dividing by 10
			creditCardNumberForEvens = creditCardNumberForEvens / 10;

		}

		// Add the sumOfOdds and sumOfEvens
		checkSum = sumOfOdds + sumOfEvens;

		// Validating if checkSum is a multiple of 10
		if (checkSum % 10 == 0) {

			return true;

		} else {

			return false;
		}
		
	}

	// Method to build expenses array
	public static double[] buildExpenses (double monthlyRent) {

		double monthlyExpenses[] = new double[12];
		double monthlyMiscellaneousExpenses = 600;
		double doctorCost = 200;
		double textbookCost = 300;
		double birthdayExpenses = 100;
		double holidayExpenses = 200;

		// for loop to fill expenses array
		for (int i = 0; i < monthlyExpenses.length; i++) {
			
			// Every month and rent and miscellaneous expenses (food, cell phone, internet, etc.)
			monthlyExpenses[i] += monthlyRent + monthlyMiscellaneousExpenses;

			// In the months of January and Jun
			if (i == 0 || i == 5) {

				// Add doctor expenses
				monthlyExpenses[i] += doctorCost;
			}

			// In the month of September
			else if (i == 8) {

				// Add textbook expenses
				monthlyExpenses[i] += textbookCost;
			}

			// In the months of April, July, and September
			else if (i == 3 || i == 6 || i == 8) {
				
				// Add birthday expenses
				monthlyExpenses[i] += birthdayExpenses;	
			}

			// In the month of December
			else if (i == 11) {

				// Add holiday expenses
				monthlyExpenses[i] = holidayExpenses;
			}
		}

		return monthlyExpenses;

	}

	// Method to create an array for monthly credit card payments
	public static double[] buildPayments (double postTaxIncome) {

		// Monthly credict card payment array
		double monthlyCreditCardPayments [] = new double[12];

		// for loop to populate payment array
		for (int i = 0; i < monthlyCreditCardPayments.length; i++) {
			
			// Every month use 10% of post-tax income to pay off credit card
			monthlyCreditCardPayments[i] += postTaxIncome * 0.10;

			// In the month of December
			if (i == 11) {

				// Use gift from family (holiday) towards paying of credit card 
				monthlyCreditCardPayments[i] += 150;
			}

			if (i == 8) {
				
				// Use gift from family (university) towards paying of credit card 
				monthlyCreditCardPayments[i] += 200;
			}

		}

		return monthlyCreditCardPayments;

	} 

	// Display monthly credit card balance
	public static void printBalance (double creditCardBalance, double yearlyInterestRate, double[] monthlyExpenses, double[] monthlyPaymets) {

		double interest = 0;

		// for loop to iterate through each month
		for (int i = 0; i < 12; i++) {

			// Add the current months expenses to the credit card balance
			creditCardBalance += monthlyExpenses[i];

			// Subtract the current months credit card payment from the credit card balance
			creditCardBalance -= monthlyPaymets[i];

			// Calculate this months interest based on balance and yearly interest rate
			interest = creditCardBalance * ((yearlyInterestRate / 100) / 12);
			// Add this months interest to the credit card balance
			creditCardBalance += interest;

			if (creditCardBalance <= 0) {
				interest = 0;
				
			}

			System.out.println("Month " + (i + 1) + " balance " + (Math.round(creditCardBalance * 100.0) / 100.0));

		}

	}













}