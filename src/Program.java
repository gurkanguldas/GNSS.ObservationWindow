
public class Program {
public static void main(String[] args) {

	/*Datas.toTLE("C:\\Users\\Gurkan\\Desktop\\gps-ops.txt", Status.Number("G01"));
	System.out.println(Datas.M);
	Datas.ClearAll();
	Datas.toTLE("C:\\Users\\Gurkan\\Desktop\\gps-ops.txt", Status.Number("G52"));
	System.out.println(Datas.M);
	*/
	Frame a = new Frame();
	
	 Frame.Lower(new Object[][]{},
			new String[] 
					{"PRN","a[km]","e[]","M[o]","l[o]","w[o]","i[o]","n[rev/day]","n2[rev/day^2]","n6[rev/day^3]","t0","",""}
			
		
	);
	 
	 Frame.Left(new Object[][]{},
				new String[] 
						{"PRN","Select"}
				
			
		);
	 a.Buton();
	 a.Chooser();
	 a.Frames();

	/*UTC e = new UTC(Datas.t0);
	GPSweek gweek = new GPSweek(e.day, e.month, e.year);
	System.out.println(e.day+"/"+e.month+"/"+e.year);
	System.out.println("GPS Haftasý = "+gweek.GPS_month);
	System.out.println("GPS Hafta Günü = "+gweek.GPS_day);*/
}
}
