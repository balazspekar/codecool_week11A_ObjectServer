package com.codecool.week11a.client.product;

import java.util.ArrayList;
import java.util.List;

import com.codecool.week11a.client.helpers.Buyable;
import com.codecool.week11a.client.person.Person;

public class Movie extends Product implements Buyable
{

	private Genre genre;
	private long duration;
	private double rate;
	private List<Person> cast = new ArrayList<Person>();
	private int price;

	public Movie(String title, Person person)
	{
		this.title = title;
		this.person = person;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}

	public long getDuration()
	{
		return duration;
	}

	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	public double getRate()
	{
		return rate;
	}

	public void setRate(double rate)
	{
		this.rate = rate;
	}

	public List<Person> getCast()
	{
		return cast;
	}

	public void setCast(List<Person> cast)
	{
		this.cast = cast;
	}

	public int getPrice()
	{
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
		for (Person person : cast)
		{
			total += person.getSalary();
		}
		return total;
	}

}
