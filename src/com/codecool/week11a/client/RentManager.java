package com.codecool.week11a.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.codecool.week11a.client.helpers.Buyable;
import com.codecool.week11a.client.helpers.Command;
import com.codecool.week11a.client.person.Gender;
import com.codecool.week11a.client.person.Person;

public class RentManager
{
	public static void main(String[] args)
	{
		System.out.println("--CLIENT\n");

		System.out.print("Instantiating Person objects: ");
		Person jkrowling = new Person();
		Person asimov = new Person();
		System.out.println("Done.");

		System.out.print("Populating Person objects: ");
		jkrowling.setFirstName("JK");
		jkrowling.setLastName("Lowling");
		jkrowling.setGender(Gender.FEMALE);
		jkrowling.setSalary(9510);
		asimov.setFirstName("Isaac");
		asimov.setLastName("Asimov");
		asimov.setGender(Gender.MALE);
		asimov.setSalary(2000);
		System.out.println("Done.\n");

		System.out.println("Some toString() magic:");
		System.out.println(jkrowling.toString());
		System.out.println(asimov.toString() + "\n");

		System.out.println("Creating the connection");
		String host = "127.0.0.1";
		int port = 7575;
		Socket client;

		try
		{
			client = new Socket(host, port);
			System.out.println("Connected to server");
			ObjectOutputStream objectToServer = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream objectFromServer = new ObjectInputStream(client.getInputStream());

			send(objectToServer, Command.PUT);
			send(objectToServer, jkrowling);

			System.out.println("Closing the connection");
			client.close();
			System.out.println("Connection closed");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public static void send(ObjectOutputStream oos, Object objectToSend)
	{
		try
		{
			oos.write(0);
			oos.writeObject(objectToSend);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public static int getBuyableProducts(List<Buyable> buyables)
	{
		int total = 0;
		for (Buyable buyable : buyables)
		{
			total += buyable.getPrice();
		}
		return total;
	}

}