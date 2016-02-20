package com.codecool.week11a.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
			ObjectOutputStream objectToClient = new ObjectOutputStream(server.getOutputStream());
			ObjectInputStream objectFromClient = new ObjectInputStream(server.getInputStream());
			System.out.println("Client connected");

			while (true)
			{
				if (objectFromClient.read() > -1)
				{
					Object incomingObject = null;
					incomingObject = objectFromClient.readObject();

					if (incomingObject instanceof Command && ((Command) incomingObject == Command.EXIT))
					{
						System.out.println("Exit command received, terminating server process");
						break; // terminating the while loop
					}

					else if (incomingObject instanceof Command && ((Command) incomingObject == Command.GET))
					{
						mode = ServerMode.LOAD;
						System.out.println("Server mode set to LOAD");
						Object requestedObjectByClient = load();
						objectToClient.writeObject(requestedObjectByClient);
					}

					else if (incomingObject instanceof Command && ((Command) incomingObject == Command.PUT))
					{
						mode = ServerMode.SAVE;
						System.out.println("Server mode set to SAVE");
					}

					else if (mode == ServerMode.SAVE)
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
		List<Object> objectList = new ArrayList<Object>();

		try
		{
			FileInputStream fis = new FileInputStream("d:\\data.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object;

			try
			{
				while ((object = ois.readObject()) != null)
				{
					objectList.add(object);
				}
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}

			ois.close();
			fis.close();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return objectList;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		ObjectServer server = new ObjectServer();
		server.runServer();
	}

}
