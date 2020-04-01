package eg.edu.alexu.csd.datastructure.stack.cs78;

import java.util.Scanner;

public class ExpressionEvaluatorUserInterface {
	public static void main(String[] args) {
		System.out.println("ExpressionEvaluator");
		ExpressionEvaluator evaluator = new ExpressionEvaluator();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		while(true) {
		System.out.println("Enter an expression to evaluate: ");
		String expression;
		expression = input.nextLine();
		String postfix = evaluator.infixToPostfix(expression);
		int result = evaluator.evaluate(postfix);
		System.out.println("postfix expression : " + postfix);
		System.out.println("evaluation : " + result );
		}
	}
}
