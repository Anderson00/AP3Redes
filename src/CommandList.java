import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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
	
	public static String listCmds(){
		StringBuffer strBuffer = new StringBuffer(50);
		Class<CommandList> commandList = CommandList.class;
		for(Method m : commandList.getMethods()){
			StringBuffer params = new StringBuffer(20);
			for(Parameter pr : m.getParameters()){
				params.append(pr.getType().getSimpleName());
				params.append(", ");
			}
			if(params.length() > 0)
				params.setLength(params.length()-2);
			String method = m.getName()+"("+params.toString()+")";
			strBuffer.append(method);
			strBuffer.append("\n");
		}
		return strBuffer.toString();
	}
	
	public static String operacao(String n1, String op, String n2) throws NumberFormatException{
		Double numero1 = Double.parseDouble(n1);
		Double numero2 = Double.parseDouble(n2);
		switch(op){
		case "+":
			return (numero1+numero2)+"";
		case "-":
			return (numero1-numero2)+"";
		case "*":
			return (numero1*numero2)+"";
		case "/":
			return (numero1/numero2)+"";
		}
		throw new NumberFormatException();
	}
	
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
