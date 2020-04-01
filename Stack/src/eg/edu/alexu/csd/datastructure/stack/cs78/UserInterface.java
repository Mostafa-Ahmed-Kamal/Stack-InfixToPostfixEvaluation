package eg.edu.alexu.csd.datastructure.stack.cs78;

import java.util.Scanner;

public class UserInterface {
public static void main(String[] args) {
	Stack S = new Stack();
	System.out.println("Stacks operations");
	while(true) {
	System.out.println("please choose an action : ");
	System.out.println("1: Push\r\n" + 
			"2: Pop\r\n" + 
			"3: Peek\r\n" + 
			"4: Get size\r\n" + 
			"5: Check if empty");
	@SuppressWarnings("resource")
	Scanner scan = new Scanner(System.in);
	char input = scan.next().charAt(0);
	scan.nextLine();
	switch(input) {
	case '1' :
		System.out.println("enter a value to push: ");
		String x = scan.nextLine();
		//if x is an integer
		if(x.matches("-?[1-9]\\d*|0")) {
			//push input as integer
			S.push(Integer.parseInt(x));
		}
		else if(x.matches("[-+]?[0-9]*\\.?[0-9]+")) {
			//push input as float
			S.push(Float.parseFloat(x));
		}
		else if(x.length()==1) {
			//push as character
			S.push((char) x.charAt(0));
		}
		else {
			//push input as String
		S.push(x);
		}
		break;
	case '2' : 
		Object pop = S.pop();
		if(pop!=null)
		System.out.println(pop);
		break;
	case '3' :
		Object peek = S.peek();
		if(peek!=null)
		System.out.println(peek);
		break;
	case '4' : 
		System.out.println("Size = "+S.size());
		break;
	case '5' :
		System.out.println(S.isEmpty());
		break;
	default : 
		System.out.println("WRONG INPUT");
		
	}
}
}
}
