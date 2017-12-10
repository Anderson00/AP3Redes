import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UDPServer{
	
	
	public static void main(String args[]){
		try {
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			DatagramSocket serverSocket = new DatagramSocket(CommandList.DEFAULT_PORT);
			while(true){
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				System.out.println("Esperando...");
				serverSocket.receive(receivePacket);
	 
				String sentence = new String(receivePacket.getData());
				Date date = Calendar.getInstance().getTime();
				SimpleDateFormat format = new SimpleDateFormat("H:m:s d/M/Y");
				System.out.println("["+format.format(date)+"]"+"["+receivePacket.getAddress().getHostAddress()+"]> "+sentence);
				
				new Thread(new UDPThread(serverSocket, receivePacket)).start();
				
			}			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static class UDPThread implements Runnable{
		
		private DatagramSocket serverSocket;
		private DatagramPacket receive;
		private byte[] sendData = new byte[1024];
		private String cmdReceived;
		
		public UDPThread(DatagramSocket serverSocket, DatagramPacket receive){
			this.serverSocket = serverSocket;
			this.receive = receive;
		}
		
		public List<String> getParams(String cmdSplit[]){
			List<String> lista = new ArrayList<String>();
			for(int i = 1; i < cmdSplit.length; i++){
				lista.add(cmdSplit[i]);
			}
			return lista;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			cmdReceived = new String(receive.getData()).trim();
			String cmdSplit[] = cmdReceived.split(" ");
			String command = cmdSplit[0];
			List<String> params = getParams(cmdSplit);
			
			sendData = "Errado".getBytes();
			Class<CommandList> commandList = CommandList.class;
			for(Method method : commandList.getMethods()){
				if(method.getName().equalsIgnoreCase(cmdSplit[0])){
					
					try {
						sendData = ((String)method.invoke(null, params.toArray())).getBytes();
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						sendData = "Comando Inválido!".getBytes();
					}
				}
			}
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receive.getAddress(), receive.getPort());
			try {
				serverSocket.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}