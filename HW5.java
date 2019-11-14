import java.lang.Math; 
import java.io.FileWriter;
import java.io.File;

public class HW5 {
	static String outfile;
	static FileWriter myfile;

	static int Precedence(char ch) {
		switch(ch) {
			case '+':
				return 1;
			case '-':
				return 1;
			case '/':
				return 2;
			case '*':
				return 2;
			case '^':
				return 3;
		}
		return -1;
	}

	static String infixToPostfix(String expression) {
		String result = new String("");

		Stack <Character> stack = new Stack<Character>();

		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (Character.isLetterOrDigit(ch)){
				result +=ch;
			} else if (ch == '(') {
				stack.add(ch);
			} else if (ch == ')') {
				while (!stack.isEmpty() && stack.top() != '(') {
					result += stack.pop();
				}

				if (!stack.isEmpty() && stack.top() != '(') {
					return "Invalid Expression!";
				} else {
					stack.pop();
				}
			} else {
				while(!stack.isEmpty() && Precedence(ch) <= Precedence(stack.top())) {
					if (stack.top() == '(') {
						return "Invalid Expression!";
					}

					result += stack.pop();
				}

				stack.add(ch);
			}
			
		}

		while(!stack.isEmpty()) {
			if (stack.top() == '('){
				return "Invalid Expression!";
			}

			result += stack.pop();
		}
		return result;
	}

	static String evaluatePostfix(String expression) { 
		String result = new String("");

		Stack<Double> stack = new Stack<Double>(); 
		  
		for(int i = 0; i < expression.length(); i++) { 
			char ch = expression.charAt(i);

			if(Character.isDigit(ch)) {
				stack.add((double)(ch - '0'));
			} else {
				if (stack.isEmpty()){
					return "nv";
				}

				Double val1 = stack.pop();

				if (stack.isEmpty()){
					return "nv";
				}

				Double val2 = stack.pop();
				
				switch(ch) {
					case '+':
					stack.add(val2 + val1);
					break;
					
					case '-':
					stack.add(val2 - val1);
					break;
					
					case '/':
					stack.add(val2 / val1);
					break;
					
					case '*':
					stack.add(val2 * val1);
					break;

					case '^':
					stack.add(Math.pow(val2,val1));
					break;
			  	}
			}
		}
		result = Double.toString(stack.pop());
		return result;
	} 
	
	public static void main(String args[]) {
		if (args.length != 3) {
			System.out.println("Invalid Arguments");
			System.exit(1);
		}
		try {
			int part = Integer.parseInt(args[0]);
			String expression = args[1];
			outfile = args[2];
			
			File file = new File(outfile);
			file.createNewFile(); // create the new file only it does not exist
			
			myfile = new FileWriter(outfile);
			
			if (part == 2) {
				myfile.write(infixToPostfix(expression));
				System.out.println("postfix:" + infixToPostfix(expression));
			} else if (part == 3) {
				myfile.write(evaluatePostfix(expression));
				System.out.println("evaluation:" + evaluatePostfix(expression));
			} else {
				System.out.println("Invalid Value of part parameter: " + part);
			}

			myfile.close();
			
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}