package dev.azucares.tilegame.networking;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import dev.azucares.tilegame.Handler;

public class Client implements Runnable {
	int port ;
	String address;
	Socket connection ;
	PrintWriter printout ;
	BufferedReader incoming ;
	BufferedOutputStream streamOut ;
	OutputStreamWriter writer ;
	Handler handler ;
	private String[] player1Coords ;
	private String inFromServer ;
	private BufferedReader br ;
	private boolean connected ;
	
	public Client(Handler handler, int port, String address){
		this.port = port ;
		this.address = address ;
		this.handler = handler ;
		
		System.out.println("creating client");
		try {
			connection = new Socket(address, port) ;
			printout = new PrintWriter(connection.getOutputStream(), true) ;
			streamOut = new BufferedOutputStream(connection.getOutputStream()) ;
			br = new BufferedReader(new InputStreamReader(connection.getInputStream())) ;
			System.out.println("client created");
			connected = true ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to connect to server");
			e.printStackTrace();
		}
	}
	
	public void communicate(){
		try {
			while(true){
				System.out.println("test point " + br.ready() );
				if(br.ready()){
					inFromServer = br.readLine() ;
					System.out.println("test point 1");
					inFromServer = br.readLine() ;
					System.out.println("test point 2");
					player1Coords = inFromServer.split(" ") ;
					handler.getWorld().getEntityManager().getPlayer().setX(Float.parseFloat(player1Coords[0]));
					handler.getWorld().getEntityManager().getPlayer().setY(Float.parseFloat(player1Coords[1]));
				}
					
					System.out.println("test point 3");	
				
				printout.println(handler.getWorld().getEntityManager().getPlayer2().getX() + " " + handler.getWorld().getEntityManager().getPlayer2().getY());
				System.out.println("test point 4");
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendToServer(){
		Thread send = new Thread(){
			public void run(){
				while(connected){
					printout.println(handler.getWorld().getEntityManager().getPlayer2().getX() + " " + handler.getWorld().getEntityManager().getPlayer2().getY());
				}
			}
		};
		send.start();
	}
	
	private void recieveFromServer(){
		Thread recieve = new Thread(){
			public void run(){
				while(connected){
					String inFromServer ;
					
					try {
						inFromServer = br.readLine() ;
						player1Coords = inFromServer.split(" ") ;
						handler.getWorld().getEntityManager().getPlayer().setX(Float.parseFloat(player1Coords[0]));
						handler.getWorld().getEntityManager().getPlayer().setY(Float.parseFloat(player1Coords[1]));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		recieve.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sendToServer() ;
		recieveFromServer() ;
	}
}
