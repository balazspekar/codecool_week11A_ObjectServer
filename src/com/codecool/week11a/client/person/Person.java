package com.codecool.week11a.client.person;

import java.io.Serializable;

public class Person implements Serializable
{

	private static final long serialVersionUID = 4426605518331866135L;
	private String firstName;
	private String lastName;
	private Gender gender;
	private int salary;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	public int getSalary()
	{
		return salary;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	@Override
	public String toString()
	{
		return firstName + " " + lastName + ", Gender: " + gender + ", Salary: " + salary;
	}

}
