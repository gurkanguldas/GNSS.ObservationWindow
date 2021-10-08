package DataAccess;

public class GPSweek {
	int Day = 0;
	int dy = 0;
	int GPS_day = 0;
	int GPS_month = 0;

	GPSweek(int day, int Month, int Year) {

		float Delta = Year - 1978;

		/********** 01/01/.... Which Day **************/
		for (int i = 0; i < Delta; i++) {
			if (i % 4 == 2)
				dy += 2;
			else
				dy += 1;
		}
		dy = dy % 7;
		/***********************************************/

		/********** Day/Month/Year Which Week **********/
		int AraMonth = 12;
		int TopDay = 0;
		for (int j = 0; j <= Delta; j++) {
			if (j + 1978 == Year) {
				AraMonth = Month;
				TopDay = Day;
				Day = day;
			} else
				Day += 31;
			switch (AraMonth) {
			case (12):
				Day += 30;
			case (11):
				Day += 31;
			case (10):
				Day += 30;
			case (9):
				Day += 31;
			case (8):
				Day += 31;
			case (7):
				Day += 30;
			case (6):
				Day += 31;
			case (5):
				Day += 30;
			case (4):
				Day += 31;
			case (3):
				if ((j + 1978) % 4.f == 0.0f)
					Day += 29;
				else
					Day += 28;
			case (2):
				Day += 31;
				break;
			}

		}
		TopDay += Day;
		GPS_month = -105;
		GPS_month += Math.ceil(TopDay / 7.0) - 1;
		/***********************************************/

		/********** Day/Month/Year Which Day ***********/
		GPS_day = (Day - 1 + dy) % 7;
		/***********************************************/
	}
}
