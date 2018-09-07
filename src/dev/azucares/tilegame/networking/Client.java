package dev.azucares.tilegame.networking;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client implements Runnable {
	int port ;
	String address;
	Socket connection ;
	BufferedOutputStream streamOut ;
	OutputStreamWriter writer ;
	
	public Client(int port, String address){
		this.port = port ;
		this.address = address ;
		System.out.println("creating client");
		try {
			connection = new Socket(address, port) ;
			streamOut = new BufferedOutputStream(connection.getOutputStream()) ;
			writer = new OutputStreamWriter(streamOut, "US-ASCII") ;
			System.out.println("prepped output");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to connect to server");
			e.printStackTrace();
		}
	}
	
	public void update(){
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			writer.write("is anyone out there?");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to send to server");
			e.printStackTrace();
		}
	}
}
