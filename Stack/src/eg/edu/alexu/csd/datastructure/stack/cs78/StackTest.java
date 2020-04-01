package eg.edu.alexu.csd.datastructure.stack.cs78;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StackTest {

	@Test
	void test() {
		Stack test = new Stack();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		for(int i =5 ; i>0 ; i--) {
			assertEquals(test.pop(),i);
		}
		Exception RuntimeException = assertThrows(RuntimeException.class, () -> 
	    {
	    	test.pop();
	    });
		String expectedMessage = "Invalid operation , Empty Stack";
	    String actualMessage = RuntimeException.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	    
	}
	@Test
	void test2() {
		Stack test = new Stack();
		assertEquals(test.size(),0);
		assertTrue(test.isEmpty());
		test.push('a');
		test.push("14");
		test.push(23);
		assertEquals(test.pop(), 23);
		assertEquals(test.pop(), "14");
		assertEquals(test.pop(), 'a');
		Exception RuntimeException = assertThrows(RuntimeException.class, () -> 
	    {
	    	test.peek();
	    });
		String expectedMessage = "Invalid operation , Empty Stack";
	    String actualMessage = RuntimeException.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
