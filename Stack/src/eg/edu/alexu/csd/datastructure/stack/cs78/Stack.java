package eg.edu.alexu.csd.datastructure.stack.cs78;

/**
 * @author Mostafa Ahmed Kamal
 * Stacks implementation using linked-lists   
 */
public class Stack implements IStack{
	Node top;
	int size=0;
	private class Node{
		Object value;
		Node next;
		Node(Object x){
			value = x;
			next = null;
		}
		Node(Object x , Node y){
			value = x;
			next = y;
		}
	}

	/**@return value
	 *return the last element inserted in the stack and removes it from the Stack
	 */
	public Object pop() {
		if(size<=0) {
			throw new RuntimeException("Invalid operation , Empty Stack");
			}
		else {
			Object value = top.value;
			top=top.next;
			size--;
			return value;
		}
		
	}

	/**
	 *returns the last element inserted in the Stack without removing it 
	 */
	public Object peek() {
		if(top== null) {
			throw new RuntimeException("Invalid operation , Empty Stack");
		}
		else
		return top.value;
	}

	/**@param element
	 *pushes an object to the stack
	 */
	public void push(Object element) {
		if(top==null)
			top = new Node(element);
		else {
			Node newNode = new Node(element , top);
			top = newNode;
		}
		size++;
	}

	/**
	 *returns true if and only if the stack is empty
	 */
	public boolean isEmpty() {
		return (size<=0);
	}

	/**
	 *returns the number of elements in the stack
	 */
	public int size() {
		return size;
	}
}
