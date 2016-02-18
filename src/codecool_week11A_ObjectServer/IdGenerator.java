package codecool_week11A_ObjectServer;

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
