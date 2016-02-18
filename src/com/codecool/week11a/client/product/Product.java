package com.codecool.week11a.client.product;

import com.codecool.week11a.client.helpers.IdGenerator;
import com.codecool.week11a.client.person.Person;

public abstract class Product
{
	protected String id;
	protected String title;
	protected Person person; // the person who rented the product

	public String getTitle()
	{
		return title;
	}

	public Person getPerson()
	{
		return person;
	}

	public abstract long getInvestment();

	public void setId()
	{
		this.id = IdGenerator.generate(this);
	}
}
