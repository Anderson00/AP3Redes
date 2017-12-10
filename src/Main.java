import java.io.InputStream;
import java.util.Scanner;

public class Main {
	
	public void commandLine(){
		CommandLine commandLine = new CommandLine();
		commandLine.start();
	}
	
	public static void main(String args[]){
		System.out.println("----------| UFTP v1.0 |----------");
		new Main().commandLine();//Inicializa linha de commando
	}
}
