package com.codecool.week11a.client.product;

import java.util.ArrayList;
import java.util.List;

import com.codecool.week11a.client.helpers.Buyable;
import com.codecool.week11a.client.person.Person;

public class Game extends Product implements Buyable
{

	private boolean preOrdered;
	private List<Person> staff = new ArrayList<Person>();
	private int price;

	public Game(String title, Person person)
	{
		this.title = title;
		this.person = person;
	}

	public boolean isPreOrdered()
	{
		return preOrdered;
	}

	public void setPreOrdered(boolean preOrdered)
	{
		this.preOrdered = preOrdered;
	}

	public List<Person> getStaff()
	{
		return staff;
	}

	public void setStaff(List<Person> staff)
	{
		this.staff = staff;
	}

	@Override
	public int getPrice()
	{
		if (preOrdered)
		{
			return (int) (price * 0.8);
		}
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	@Override
	public long getInvestment()
	{
		long total = 0;
		for (Person person : staff)
		{
			total += person.getSalary();
		}
		return total;
	}

}
