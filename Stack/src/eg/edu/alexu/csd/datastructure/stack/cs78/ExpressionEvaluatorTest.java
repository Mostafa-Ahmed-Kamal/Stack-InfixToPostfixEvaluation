package eg.edu.alexu.csd.datastructure.stack.cs78;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpressionEvaluatorTest {

	@Test
	void test1() {
		//test for normal valid and invalid entries 
		ExpressionEvaluator test = new ExpressionEvaluator();
		String infix = "3*2 + 5/(1+4) + 5%2";
		assertEquals(8,test.evaluate(test.infixToPostfix(infix)));
		infix = new String("130 / 81 + 32 -3 ");
		assertEquals("130 81 / 32 + 3 - " , test.infixToPostfix(infix));
		assertEquals(31,test.evaluate(test.infixToPostfix(infix)));
		//exception test
		Exception RuntimeException = assertThrows(RuntimeException.class, () -> 
	    {
	    	test.evaluate(test.infixToPostfix("50/0"));
	    });
		String expectedMessage = "dividing by zero is forbiden";
	    String actualMessage = RuntimeException.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	void test2() {
		ExpressionEvaluator test = new ExpressionEvaluator();
		//Testing bad but valid entries//
		String infix = "1--3";
		assertEquals(4,test.evaluate(test.infixToPostfix(infix)));
		infix = new String("1---3");
		assertEquals(-2,test.evaluate(test.infixToPostfix(infix)));
		infix = new String("   1   -  -  -     3");
		assertEquals(-2,test.evaluate(test.infixToPostfix(infix)));
		infix = new String("1----3");
		assertEquals(4,test.evaluate(test.infixToPostfix(infix)));
		infix = new String("3*-3");
		assertEquals(-9,test.evaluate(test.infixToPostfix(infix)));
		//exception test
		Exception RuntimeException = assertThrows(RuntimeException.class, () -> 
	    {
	    	test.evaluate(test.infixToPostfix("3-*4"));
	    });
		String expectedMessage = "invalid input";
	    String actualMessage = RuntimeException.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	}
