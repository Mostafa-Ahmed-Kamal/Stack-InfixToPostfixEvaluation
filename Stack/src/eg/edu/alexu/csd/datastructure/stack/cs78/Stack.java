package eg.edu.alexu.csd.datastructure.stack.cs78;

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

	public Object peek() {
		if(top== null) {
			throw new RuntimeException("Invalid operation , Empty Stack");
		}
		else
		return top.value;
	}

	public void push(Object element) {
		if(top==null)
			top = new Node(element);
		else {
			Node newNode = new Node(element , top);
			top = newNode;
		}
		size++;
	}

	public boolean isEmpty() {
		return (size<=0);
	}

	public int size() {
		return size;
	}
}
