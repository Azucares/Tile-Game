package dev.azucares.tilegame.networking;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
	private ServerSocket server;
	private Socket connection;
	private BufferedReader input;
	private PrintWriter output;
	private String in ;

	private void waitForConnection() throws IOException {
		
		System.out.println("waiting for Connection\n");
		connection = server.accept();
		System.out.println("Connection recieved from "
				+ connection.getInetAddress().getHostName());
	}
	
	private void connected(){
		try {
			input = new BufferedReader(new InputStreamReader(connection.getInputStream())) ;
			output = new PrintWriter(connection.getOutputStream(), true) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("streams set");
		try {
			
			
			while(true){
				System.out.println( (in = input.readLine()) == null);
				
				System.out.println(in);
			}
			
		} catch (IOException e) {
			System.out.println("could not read input");
			e.printStackTrace();
		}
		
	}
/*
	private void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		System.out.println("got outputStream");
		System.out.println(connection.getInputStream().toString());
		//input = new ObjectInputStream(connection.getInputStream());
		
		System.out.println("got I/O streams");
	}

	private void processConnection() throws IOException {
		System.out.println("reading input");
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())) ;
		System.out.println("got input");
		System.out.println(br.readLine());
	}

	
	private void sendData(String message) {
		try {
			output.writeObject("SERVER>> " + message);
			output.flush();
		} catch (IOException ioException) {
			System.out.println("error writing object");
		}
	}
*/
	private void closeConnection() {
		System.out.println("\nterminating connection");
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			server = new ServerSocket(36000, 5);
			while (true) {
				try {
					waitForConnection();
					connected();
				} catch (EOFException eofException) {
					System.out.println("Server Terminated connection");
				} finally {
					closeConnection();
				}
			}

		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
}
