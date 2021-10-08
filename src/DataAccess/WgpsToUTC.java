package DataAccess;

public class WgpsToUTC {
	
	private int day, month, year;

	public WgpsToUTC(double t0 , double wgps) 
	{
		year = (int)wgps;
		day = (int) t0;
		
		if (t0 - day != 0.0)
			day++;
		
		month = 1;
		
		switch (month) 
		{
		case (1):
			if (day > 31.0) 
			{
				month++;
				day -= 31;
			}
		case (2): {
			if (day > 29.0 && year % 4 == 0) 
			{
				month++;
				day -= 29;
			} 
			else 
			{
				month++;
				day -= 28;
			}
		}
		case (3):
			if (day > 31.0) 
			{
				month++;
				day -= 31;
			}
		case (4):
			if (day > 30.0) 
			{
				month++;
				day -= 30;
			}
		case (5):
			if (day > 31.0) 
			{
				month++;
				day -= 31;
			}
		case (6):
			if (day > 30.0) 
			{
				month++;
				day -= 30;
			}
		case (7):
			if (day > 31.0) 
			{
				month++;
				day -= 31;
			}
		case (8):
			if (day > 31.0) 
			{
				month++;
				day -= 31;
			}
		case (9):
			if (day > 30.0) 
			{
				month++;
				day -= 30;
			}
		case (10):
			if (day > 31.0) 
			{
				month++;
				day -= 31;
			}
		case (11):
			if (day > 30.0) 
			{
				month++;
				day -= 30;
			}
		}
	}

	public  int MaxDay(int Month, int Year) {
		int maxDay = 0;
		
		if (Month == 1)
			maxDay = 31;
		
		else if (Month == 2) 
		{
			if (Year % 4 == 0)
				maxDay = 29;
			else
				maxDay = 28;
		} 
		
		else if (Month == 3)
			maxDay = 31;
		
		else if (Month == 4)
			maxDay = 30;
		
		else if (Month == 5)
			maxDay = 31;
		
		else if (Month == 6)
			maxDay = 30;
		
		else if (Month == 7)
			maxDay = 31;
		
		else if (Month == 8)
			maxDay = 31;
		
		else if (Month == 9)
			maxDay = 30;
		
		else if (Month == 10)
			maxDay = 31;
		
		else if (Month == 11)
			maxDay = 30;
		
		else if (Month == 12)
			maxDay = 31;
		
		else
			maxDay = 0;
		
		return maxDay;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
}
