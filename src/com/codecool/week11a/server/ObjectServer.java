package com.codecool.week11a.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.codecool.week11a.client.helpers.Command;

public class ObjectServer
{
	private ServerMode mode = ServerMode.LOAD;
	private final int PORT = 7575;

	public void runServer() throws ClassNotFoundException
	{
		System.out.println("--SERVER\n");

		ServerSocket serverSocket;
		try
		{
			serverSocket = new ServerSocket(PORT);
			System.out.println("Starting ServerSocket on port " + PORT);
			System.out.println("Waiting for an incoming connection on " + InetAddress.getLocalHost().getHostAddress());
			Socket server = serverSocket.accept();
			ObjectInputStream objectFromClient = new ObjectInputStream(server.getInputStream());
			ObjectOutputStream objectToClient = new ObjectOutputStream(server.getOutputStream());
			System.out.println("Client connected");

			while (true)
			{
				if (objectFromClient.read() > -1)
				{
					Object incomingObject = null;
					incomingObject = objectFromClient.readObject();

					if (incomingObject instanceof Command && ((Command) incomingObject == Command.PUT))
					{
						mode = ServerMode.SAVE;
						System.out.println("Mode set to SAVE");

					} else if (incomingObject instanceof Command && ((Command) incomingObject == Command.GET))
					{
						mode = ServerMode.LOAD;
						System.out.println("Mode set to LOAD");
						// Object obj = load();
						// objectToClient.writeObject(obj);
					} else if (incomingObject instanceof Command && ((Command) incomingObject == Command.EXIT))
					{
						System.out.println("Exit command received");
						break;
					} else if (mode == ServerMode.SAVE)
					{
						save(incomingObject);
						System.out.println("Calling save() method: done");
					}

				}

			}

			server.close();
			serverSocket.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public void save(Object object)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream("d:\\data.dat", false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
			fos.close();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public List<Object> load()
	{
		return null;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		ObjectServer server = new ObjectServer();
		server.runServer();
	}

}
