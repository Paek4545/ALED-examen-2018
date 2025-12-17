package es.upm.aled.login;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		try {
		ServerSocket servidor = new ServerSocket(10007);
		Socket cliente = servidor.accept();
		OutputStream out = cliente.getOutputStream();
		InputStream in = cliente.getInputStream();
		byte[] bytes = new byte[4096];
		while((in.read(bytes)) > 0) {
			out.write(bytes);
		}	
		out.close();
		in.close();
		cliente.close();
		servidor.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
