import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class SocketClient {
	private DatagramSocket cliente;
	private InetAddress IPAddress;
	private int port;
	private boolean error;
	
	public SocketClient(String ip, int port){
		this.port = port;
		error = startSocket(ip, port);
	}
	
	public boolean startSocket(String ip, int port){
		try {
			cliente = new DatagramSocket();
			cliente.setSoTimeout(2000);
			IPAddress = InetAddress.getByName(ip);		
			return true;
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	
	public String sendCommand(String cmd){
		try {
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			sendData = cmd.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			cliente.send(sendPacket);
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			String resposta = "";
			try{
				cliente.receive(receivePacket);      
	        	resposta = new String( receivePacket.getData());
			}catch(SocketTimeoutException e){
				System.out.println("Timeout reached!!! " + e);	            
			}
	        
	        return resposta;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean serverExists(){
		return error;
	}
}
