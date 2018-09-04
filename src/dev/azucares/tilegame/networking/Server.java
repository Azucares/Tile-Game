package dev.azucares.tilegame.networking;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
	ServerSocket server;
	Socket connection;
	ObjectInputStream input;
	ObjectOutputStream output;

	private void waitForConnection() throws IOException {
		System.out.println("waiting for Connection\n");
		connection = server.accept();
		System.out.println("Connection recieved from "
				+ connection.getInetAddress().getHostName());
	}

	private void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();

		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("got I/O streams");
	}

	private void processConnection() throws IOException {
		sendData("Hello there!") ;
	}

	private void sendData(String message) {
		try {
			output.writeObject("SERVER>> " + message);
			output.flush();
		} catch (IOException ioException) {
			System.out.println("error writing object");
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			server = new ServerSocket(36000, 5);
			while (true) {
				try {
					waitForConnection();
					getStreams();
					processConnection();
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
