import java.util.ArrayList;

public class Stack<X> {
	private ArrayList<X> stack = null;
	
	public Stack() {
		stack = new ArrayList<X>();
	}

	public X top() {
		return stack.get(stack.size() - 1);
	}

	public X pop() {
		if (stack.size() == 0)
			throw new IllegalStateException("Empty Stack");
		return stack.remove(stack.size() - 1);
	}

	public void add(X item) {
		stack.add(item);
	}

	public int size() {
		return stack.size();
	}

	public void print() {
		System.out.println(stack);
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}
}