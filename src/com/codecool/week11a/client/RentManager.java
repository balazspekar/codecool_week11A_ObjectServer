package com.codecool.week11a.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.codecool.week11a.client.helpers.Buyable;
import com.codecool.week11a.client.person.Gender;
import com.codecool.week11a.client.person.Person;

public class RentManager
{
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		System.out.println("--CLIENT\n");

		System.out.print("Instantiating Person objects: ");
		Person jkrowling = new Person();
		Person asimov = new Person();
		System.out.println("Done.");

		System.out.print("Populating Person objects: ");
		jkrowling.setFirstName("JK");
		jkrowling.setLastName("Rowling");
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
		Socket client = new Socket(host, port);
		System.out.println("Connected to server");

		ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());

		objectOutputStream.writeObject(jkrowling);

		System.out.println("Closing the connection");
		client.close();
		System.out.println("Connection closed");
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