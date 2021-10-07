

public class Values{
	static double n,n2,n6,M,ik,w,l,e,t0;
	static double X , Y, Z,tk;
	static String satalite;
	static UTC time;
    public static void TLE(double hour)
    {
    	time = new UTC(tk);
    	GPSweek gweek = new GPSweek(time.day, time.month, time.year);
        double Wgps = gweek.GPS_month;
        double LeapSec = 0.0;
        if (satalite.substring(0, 1).equalsIgnoreCase("C"))
        {
            if (1356 < Wgps && Wgps <= 1512) LeapSec = 0.0 / 86400.0;
            else if (1512 < Wgps && Wgps <= 1669) LeapSec = 1.0 / 86400.0;
            else if (1669 < Wgps && Wgps <= 1825) LeapSec = 2.0 / 86400.0;
            else if (1825 < Wgps && Wgps <= 1930) LeapSec = 3.0 / 86400.0;
            else LeapSec = 4.0 / 86400.0;
        }
        else
        {
            if (990 <= Wgps && Wgps <= 1356) LeapSec = 13.0 / 86400.0;
            else if (1356 < Wgps && Wgps <= 1512) LeapSec = 14.0 / 86400.0;
            else if (1512 < Wgps && Wgps <= 1669) LeapSec = 15.0 / 86400.0;
            else if (1669 < Wgps && Wgps <= 1825) LeapSec = 16.0 / 86400.0;
            else if (1825 < Wgps && Wgps <= 1930) LeapSec = 17.0 / 86400.0;
            else LeapSec = 18.0 / 86400.0;
        }
        
        Coordinate XYZ = new Coordinate(satalite);
        double GM = 0.0;
        if (satalite.substring(0, 1).equalsIgnoreCase("G")) { GM = 398600.5; }
        else if (satalite.substring(0, 1).equalsIgnoreCase("R")) { GM = 398600.4418; }
        else if (satalite.substring(0, 1).equalsIgnoreCase("E")) { GM = 398600.4418; }
        else { GM = 398600.4418; }
            double dt = gweek.Day-1 + hour / 24.0 - t0 - LeapSec;
            double nk = (n + n2 * dt+ n6 * dt*dt) / 86400.0 * 2.0 * Math.PI;
            double a = Math.pow(GM / nk / nk, 1.0 / 3.0);
            double Mk = M / 180.0 * Math.PI + nk * dt * 86400.0;
            Mk = Mk % (2.0 * Math.PI);
            while (Mk < 0.0)
                Mk += 2.0 * Math.PI;
            double Ek0 = Mk + e * Math.sin(Mk);
            double Ek = Mk + e * Math.sin(Ek0);
            while (Math.abs(Ek - Ek0) >= 1.0e-5)
            {
            	//System.out.println("EK ITER...");
                Ek0 = Ek;
                Ek = Mk + e * Math.sin(Ek0);
            }
            double rk = a * (1.0 - e * Math.cos(Ek));
            int month = time.month;
            int year = time.year;
            int day =time.day;
            if (month <= 2) { month += 12; year--; }
            double JD = (int)(365.25 * year) + (int)(30.6001 * (month + 1.0)) + day + hour / 24.0 + 1720981.5 - LeapSec;

            

            double JD2000 = 2451545.0;
            double D = JD - JD2000;
            double D0 = (int)D - 0.5;
            double H = (D - D0) * 24.0;
            double T = D / 36525.0;
            double GMST = 6.697374558 + 0.06570982441908 * D0 + 1.00273790935 * H + 0.000026 * T * T;
            double Q = (125.04 - 0.052954 * D) / (180.0 / Math.PI);
            double L = (208.47 + 0.98565 * D) / (180.0 / Math.PI);
            double E = (23.4393 - 0.0000004 * D) / (180.0 / Math.PI);
            double K = -0.000319 * Math.sin(Q) - 0.000024 * Math.sin(2.0 * L);
            double eqeq = K * Math.cos(E);
            double GAST = GMST + eqeq;
            GAST = (GAST % 24.0) / 12.0 * Math.PI;
            double lk = l / 180.0 * Math.PI - GAST;
            
            double c = Math.sqrt(1 - e * e) * Math.sin(Ek), v = (Math.cos(Ek) - e);
            double fk = 0.0;
            if (c > 0.0 && v > 0.0) fk = Math.atan(c / v);
            else if (c > 0.0 && v < 0.0) fk = Math.atan(c / v) + Math.PI;
            else if (c < 0.0 && v < 0.0) fk = Math.atan(c / v) + Math.PI;
            else fk = Math.atan(c / v) + 2.0 * Math.PI;

            double uk = w / 180.0 * Math.PI + fk;
            double[] xyz = XYZ.toPlace(rk, uk, ik / 180.0 * Math.PI, lk);
            X = xyz[0]*1.0e3; Y = xyz[1]*1.0e3; Z = xyz[2]*1.0e3;
        
    }

}