package com.codecool.week11a.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.codecool.week11a.client.person.Person;

public class ObjectServer
{
	private ServerMode mode;
	private final int PORT = 7575;

	public void runServer() throws IOException, ClassNotFoundException
	{

		System.out.println("--SERVER\n");
		System.out.println("Starting ServerSocket on port " + PORT);
		ServerSocket serverSocket = new ServerSocket(PORT);

		System.out.println("Waiting for an incoming connection on " + InetAddress.getLocalHost().getHostAddress());
		Socket server = serverSocket.accept();
		System.out.println("Client connected");

		ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());

		Person p = (Person) objectInputStream.readObject();
		System.out.println("Got this from client: " + p.toString());

		System.out.println("Closing ServerSocket on port " + PORT);
		server.close();

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		ObjectServer server = new ObjectServer();
		server.runServer();
	}

}
