
public class CommandList {
	public static final int DEFAULT_PORT = 10000;
	public static final String HELP = "?"; //to request help or information about the commands
	public static final String HELP2 = "help";
	public static final String BYE = "bye";
	public static final String QUIT = "quit";
	
	public static final String ADD = "add";
	public static final String SUB = "sub";
	
	public static final String OPEN = "open";
	public static final String CLOSE = "close";
	
	private CommandList(){}
	
	public static String add(String n1, String n2) throws NumberFormatException{
		String total = Double.parseDouble(n1) + Double.parseDouble(n2)+"";		
		return total;		
	}
	
	public static String sqrt(String num) throws NumberFormatException{
		return Math.sqrt(Double.parseDouble(num))+"";
	}
	
	public static String fibonacci(String fibn) throws NumberFormatException{
		return fibonacci_recursive(Integer.parseInt(fibn))+"";
	}
	
	private static int fibonacci_recursive(int fib){
		if(fib == 1 || fib == 2)
			return 1;
		return fibonacci_recursive(fib-1)+fibonacci_recursive(fib-2);
	}
}
