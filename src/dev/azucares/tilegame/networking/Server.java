package dev.azucares.tilegame.networking;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.entities.creatures.Player;

public class Server implements Runnable {
	private ServerSocket server;
	private Socket connection;
	private InputStream input;
	private OutputStream output;
	private String in ;
	private BufferedReader br ;
	private Handler handler ;
	private String[] player2Coords ;
	private boolean connected ;
	PrintWriter printout ;

	public Server(Handler handler){
		this.handler = handler ;
	}
	
	private void serverInitialize(){
		Thread waitForConnection = new Thread(){
			public void run(){
				System.out.println("waiting for connection");
				try {
					server = new ServerSocket(36000, 5);
					connection = server.accept() ;
					System.out.println("Connection recieved from " + connection.getInetAddress().getHostName());
					connectionFound() ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("stopped waiting for connection");
				}
			}
		};
		waitForConnection.start();
	}
	
	public void connectionFound(){
		try {
			input = connection.getInputStream() ;
			printout = new PrintWriter(connection.getOutputStream(), true) ;
			br = new BufferedReader(new InputStreamReader(input)) ;
			connected = true ;
			sendToClient() ;
			recieveFromClient() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to process connection");
			e.printStackTrace();
		}
		
		handler.getWorld().getEntityManager().addPlayer(new Player(handler, 400, 400));
	}
	
	private void waitForConnection() throws IOException {
		
		System.out.println("waiting for Connection\n");
		connection = server.accept();
		connected = true ;
		System.out.println("Connection recieved from " + connection.getInetAddress().getHostName());
		
	}
	
	private void sendToClient(){
		Thread send = new Thread(){
			public void run(){
				while(connected){
					printout.println(handler.getWorld().getEntityManager().getPlayer().getX() + " " + handler.getWorld().getEntityManager().getPlayer().getY());
				}
			}
		};
		send.start();
	}
	
	private void recieveFromClient(){
		Thread recieve = new Thread(){
			public void run(){
				while(connected){
					String inFromClient ;
					
					try {
						inFromClient = br.readLine() ;
						player2Coords = inFromClient.split(" ") ;
						handler.getWorld().getEntityManager().getPlayer2().setX(Float.parseFloat(player2Coords[0]));
						handler.getWorld().getEntityManager().getPlayer2().setY(Float.parseFloat(player2Coords[1]));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		recieve.start();
	}
	
	private void connected(){
		try {
			input = connection.getInputStream() ;
			printout = new PrintWriter(connection.getOutputStream(), true) ;
			br = new BufferedReader(new InputStreamReader(input)) ;
			
			handler.getWorld().getEntityManager().addPlayer(new Player(handler, 400, 400));
			
			String inFromClient ;
			
			if(br.ready()){
				inFromClient = br.readLine() ;
				player2Coords = inFromClient.split(" ") ;
				handler.getWorld().getEntityManager().getPlayer2().setX(Float.parseFloat(player2Coords[0]));
				handler.getWorld().getEntityManager().getPlayer2().setY(Float.parseFloat(player2Coords[1]));	
			}
			printout.println(handler.getWorld().getEntityManager().getPlayer().getX() + " " + handler.getWorld().getEntityManager().getPlayer().getY());	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				input.close();
				output.close();
				br.close() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}		
	}

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

	public void run(){
		System.out.println("running server");
		serverInitialize() ;
	}
	/*
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
	}*/
}
