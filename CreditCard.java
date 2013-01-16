import java.util.Vector;
import java.util.Scanner;

public class CreditCard {
	private Vector<Integer> cc_num, copy;
	private int i, temp, sum;
	private String s;
	
	public CreditCard(String cnum) {
		//Instantiating cc_num
		cc_num = new Vector<Integer>();
		//Loops through String for numbers
		for(i = 0; i < cnum.length(); i++) {
			if(Character.isDigit(cnum.charAt(i)))	
				cc_num.add(Integer.parseInt(Character.toString(cnum.charAt(i))));
		}
	}
	
	//Determines validity of a card using Luhn's algorithm
	@SuppressWarnings("unchecked")
	public boolean isValid() {
		//Instantiating copy
		copy = new Vector<Integer>();
		//copy gets an object returned casted as Vector<Integer> from the clone method
		copy = (Vector<Integer>) cc_num.clone();
		//Finds where to start algorithm
		temp = copy.size() % 2;
		for(i = 0; i < copy.size(); i += 2) {
			if(copy.elementAt(i) * 2 > 9)
				temp = copy.elementAt(i) * 2 - 9;
			else
				temp = copy.elementAt(i) * 2;
			copy.setElementAt(temp, i);
		}
		for(int x : copy) {
			sum += x;
		}	
		return sum % 10 == 0;
	}
	
	//Returns the card # and the issuer of the card and whether the card is valid or not using Luhn's algorithm
	public String toString() {
		//String needs to be initialized as something
		s = "";
		for(int x : cc_num) {
			//Adds integers to the back of the String
			s = s.concat(Integer.toString(x));
		}
		if(isValid())
			return s + " was issued by " + getIssuer() + " and is valid.";
		else
			return s + " was issued by " + getIssuer() + " and is not valid.";
	}
	
	//Finds issuer of the card based on ISO and ANSI specifications
	public String getIssuer() {
		if(cc_num.size() == 15)
			if(cc_num.elementAt(0) == 3 && cc_num.elementAt(1) == 4 || cc_num.elementAt(0) == 3 && cc_num.elementAt(1) == 7)
				return "American Express";
			else
				return "Unkown";
		else if(cc_num.size() == 13)
			return "VISA";
		else if(cc_num.size() == 14)
			return "Diner's Club";
		else if(cc_num.size() == 16) {
			if(cc_num.elementAt(0) == 4)
				return "VISA";
			else if(cc_num.elementAt(0) == 5 && cc_num.elementAt(1) >= 1 && cc_num.elementAt(1) <= 5)
				return "MasterCard";
			else if(cc_num.elementAt(0) == 6 && cc_num.elementAt(1) == 0 && cc_num.elementAt(2) == 1 && cc_num.elementAt(3) == 1)
				return "Discover";
			else
				return "Unkown";
		}
		else
			return "Unkown";
	}
	
	//Receives input from the user, then displays valid cards and issuer along with credit card #
	public static void main(String[] args) {
		//Vector of object CreditCard
		Vector<CreditCard> input = new Vector<CreditCard>();
		Scanner keys = new Scanner(System.in);
		
		System.out.println("== Credit Card Batch Validator ==");
		System.out.println("Enter card number:");
		//Loop for credit card # inputs
		do {
			System.out.println("Enter card number:");
			//Add object CreditCard with arguments supplied to parameter to Vector<CreditCard> input
			input.add(new CreditCard(keys.nextLine()));
		} while(keys.hasNext());
		
		System.out.println("All input received.  Processing...");
		//Retrieve info regarding credit card # received from user
		for(CreditCard x : input) {
			System.out.println(x.toString());
		}
		
		System.exit(0);
	}
}

