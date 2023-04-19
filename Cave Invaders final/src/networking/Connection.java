package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

	private final InputStream in;
	private final OutputStream out;
	
	
	public Connection(int port) throws IOException {
		ServerSocket server = new ServerSocket(port);
		
		Socket client = server.accept();
		in = client.getInputStream();
		out = client.getOutputStream();
		
		server.close();
	}
	
	public Connection(String ip, int port) throws UnknownHostException, IOException {
		Socket client = new Socket(ip, port);
		in = client.getInputStream();
		out = client.getOutputStream();
		client.close();
	}
	
	
	
}
