package eg.edu.alexu.csd.datastructure.stack.cs78;

import java.util.Scanner;

public class ExpressionEvaluator implements IExpressionEvaluator{
	public int priority(char op) {
		//operators priority
		if(op == '+' || op =='-')
			return 1;
		else if(op == '*' || op =='/' || op == '%')
			return 2;
		else if(op == '^')
			return 3;
		else
			return 0;
	}
	public String infixToPostfix(String expression) {
		String postfix ="";
		Stack operations = new Stack();
		//remove all unwanted characters in the string so we can postfix it the right way
		expression = expression.replace(" ","");
		for(int i=0;i<4 ;i++) {
		expression = expression.replace("++","+");	
		expression = expression.replace("--", "+");
		expression = expression.replace("+-","-");
		expression = expression.replace("-+","-");
		}
		for(int i=0 ; i< expression.length() ; i++) {
			if(expression.charAt(i)=='-') {
				// check if its a unary  minus or an operator
				if(i<1) {
					//-a --------> 0-a
					expression = expression.substring(0,i) + "0-" + expression.substring(i+1);
					i++;
				}
				//(-a+b) --------------> (0-a+b)
				else if(expression.charAt(i-1) =='(') {
					expression = expression.substring(0,i) + "0-" + expression.substring(i+1);
					i++;
				}
				// a*-b -------> a*(0-b)
				else if(expression.charAt(i-1)=='*'|| expression.charAt(i-1)=='/' || expression.charAt(i-1)=='%' || expression.charAt(i-1)=='^') {
					String token="(0-";
					int start = i;
					int end = i+1;
					if((expression.charAt(end)>='a' && expression.charAt(end)<='z') ||  (expression.charAt(end)<='Z') && expression.charAt(end)>='A') {
						token+= expression.charAt(end);
						end ++;
					}
					else if(Character.isDigit(expression.charAt(end))) {
					while(end<expression.length() && Character.isDigit(expression.charAt(end))) {
						token+=expression.charAt(end);
						end++;
					}
					}
					token += ")";
					expression = expression.substring(0,start) + token + expression.substring(end);
					i+=3;
				}
			}
		}
		// at this point the infix is ready to be transformed to postfix
		for(int i=0; i< expression.length() ; i++) {
			//push brackets into a stack
			if(expression.charAt(i) == '(') {
				operations.push(expression.charAt(i));
			}
			// pop all till the opening bracket
			else if(expression.charAt(i) == ')') {
				while(!operations.isEmpty() && (char) operations.peek() != '(') {
					postfix += operations.pop() + " ";
				}
				operations.pop();
			}
			else if(expression.charAt(i)== '+' || expression.charAt(i)== '-' || expression.charAt(i)== '*' || expression.charAt(i)== '/' || expression.charAt(i)== '^' || expression.charAt(i)== '%') {
				while(!operations.isEmpty() && priority(expression.charAt(i)) <= priority((char)operations.peek())) {
					postfix += operations.pop() + " ";
				}
				operations.push(expression.charAt(i));
			}
			//digits and variables are inserted in the postfix String
			else if(Character.isDigit(expression.charAt(i))){
				String token = "" ;
				while(i<expression.length() && Character.isDigit(expression.charAt(i))) {
					token += expression.charAt(i);
					i++;
				}
				i--;
				postfix += token + " ";
			}
			else if((expression.charAt(i) <= 'z' && expression.charAt(i) >='a') || (expression.charAt(i) <= 'Z' && expression.charAt(i) >= 'A')) {
				postfix += expression.charAt(i) + " ";
				}
		}
		// pop stack till empty in the end of the postfix string
		while(!operations.isEmpty()) {
			postfix += operations.pop() + " ";
		}
		return postfix;
	}
	public float operation(float op1 , float op2 , String operand) {
		//do the operation according to the operator and return the result to be pushed to the stack
		if(operand.contentEquals("+"))
			return op1 + op2;
		else if(operand.contentEquals("-"))
			return op1 - op2;
		else if(operand.contentEquals("*"))
			return op1 * op2;
		else if(operand.contentEquals("/"))
			if(op2 == 0 ) {
				throw new RuntimeException("dividing by zero is forbiden");
			}
			else {
				// exception if divided by zero
			return op1 / op2;
			}
		else if(operand.contentEquals("%"))
			// exception if divided by zero
			if(op2 == 0 ) {
				throw new RuntimeException("dividing by zero is forbiden");
			}
			else {
			return op1 % op2;
			}
		else if(operand.contentEquals("^"))
			return  (float) Math.pow(op1 , op2);
		else return 0;
	}
	public int evaluate(String expression) {
		//check if there are some variable in the postfix and ask the user to give these variables a value
		boolean noChar = false; 
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(!noChar) {
			noChar=true;
			for(int i=0; i<expression.length() ; i++) {
				if((expression.charAt(i) <= 'z' && expression.charAt(i) >='a') || (expression.charAt(i) <= 'Z' && expression.charAt(i) >= 'A')) {
					noChar = false;
					System.out.println("enter a value for "+ expression.charAt(i));
					String input = scan.nextLine();
					boolean acceptedValue = false ;
					while(!acceptedValue) {
						if(input.matches("-?[1-9]\\d*|0")) {
							acceptedValue = true;
						}
						else {
							System.out.println("WRONG INPUT");
							System.out.println("enter a value for "+ expression.charAt(i));
							input = scan.nextLine();
						}
					}
					expression = expression.replace(Character.toString(expression.charAt(i)), input);
				}
			}
		}
		//evaluation of numeric postfix 
		Stack digits = new Stack();
		String[] postfix = expression.split("\\s+");
		for(int i =0 ; i< postfix.length ; i++) {
			if(postfix[i].matches("-?[1-9]\\d*|0")) {
				digits.push(Float.parseFloat(postfix[i]));
			}
			else {
				float op1,op2;
				if(!digits.isEmpty()) {
					//if at any moment the stack doesn't have 2 numbers before an operator then the equation inserted is wrong
				op2 =  (float) digits.pop();
				}
				else {throw new RuntimeException("invalid input");
				}
				if(!digits.isEmpty()) {
				op1 =  (float) digits.pop();
				}
				else {throw new RuntimeException("invalid input");
				}
				float result = operation(op1,op2,postfix[i]);
				digits.push(result);
			}
		}
		// if stack have more then 1 value --> result then the equation is wrong
		if(digits.size()!=1) {
			throw new RuntimeException("invalid input");
		}
		else {
		return Math.round((float)digits.peek()) ;
		}
		}
	}
