package com.codecool.week11a.client.helpers;

import com.codecool.week11a.client.product.Book;
import com.codecool.week11a.client.product.Game;
import com.codecool.week11a.client.product.Movie;
import com.codecool.week11a.client.product.Product;

public class IdGenerator
{
	public static String generate(Product product)
	{

		long randNum = System.nanoTime();
		String result = "";
		if (product instanceof Movie)
		{
			result = "MOV" + randNum;
		} else if (product instanceof Book)
		{
			result = "BOO" + randNum;
		} else if (product instanceof Game)
		{
			result = "GAM" + randNum;
		}
		return result;
	}
}
