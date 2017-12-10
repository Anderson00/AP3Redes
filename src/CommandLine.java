import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class CommandLine {
	
	private SocketClient client;
	
	public CommandLine(){
		
	}
	
	public CommandLine(InputStream stream){
		
	}
	
	public void start(){
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.print("> ");
			String cmd = input.nextLine();
			String cmdSplit[] = cmd.split(" ");
			
			if(cmdSplit[0].equals(CommandList.OPEN)){	
				if(client != null && client.serverExists()){
					System.out.println("Já esta associado");
					break;
				}
				if(cmdSplit.length > 1){
					String ip = cmdSplit[1];
					String pattern = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):\\d{1,5}";
					if(ip.matches(pattern)){
						String ipSplit[] = ip.split(":");
						if(ipSplit.length > 1)
							client = new SocketClient(ipSplit[0],Integer.parseInt(ipSplit[1]));						
						else
							client = new SocketClient(ipSplit[0],CommandList.DEFAULT_PORT);
						if(client.serverExists())
							System.out.println("Servidor conectado!");
					}else{
						System.out.println("Comando inválido");
					}
				}else{
					System.out.println("Comando inválido");
				}		
			}else{	
				if(client != null && client.serverExists()){
					
					String response = client.sendCommand(cmd);
					System.out.println(response);
				}else{
					System.out.println("Não contectado");
				}
			}
		}
	}
}
