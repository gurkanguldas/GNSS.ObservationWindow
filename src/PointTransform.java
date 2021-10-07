
public class PointTransform {
	static double B, L, h;
	static double Xs, Ys, Zs;
    static double a, f;
    static double Alfa, S, Zr;
	 public static void Geodetic_Curve(double X, double Y, double Z)
	    {
	        double b = a * (1.0 - f);
	        double e = (2.0 * f - f * f) / (1.0 - 2.0 * f + f * f);
	        double c = a * a / b;
	        double T = Z * (1.0 + e) / Math.sqrt(X * X + Y * Y);
	        double T0;
	        do
	        {
	            T0 = T;
	            double C = 1.0 / (1.0 + T0 * T0);
	            double N = c / Math.sqrt(1.0 + e * C);
	            h = Math.sqrt((X * X + Y * Y) / C) - N;
	            T = Z * (1.0 + e) / Math.sqrt(X * X + Y * Y) / (1.0 + e * h / (N + h));
	          //  System.out.println("CURVE ITER...");
	        } while (Math.abs(T - T0) > 1.0e-5);
	        B = Math.atan(T);
	        if(X<0.0 )
	        L = -(Math.asin(Y / Math.sqrt(X * X + Y * Y)))+Math.PI;
	        else
	        	L = Math.asin(Y / Math.sqrt(X * X + Y * Y));
	        }
	 // OVERLOADING Geodetic_Curve
	 public static void Geodetic_Curve()
	    {
	        double b = a * (1.0 - f);
	        double e = (2.0 * f - f * f) / (1.0 - 2.0 * f + f * f);
	        double c = a * a / b;
	        double T = Zs * (1.0 + e) / Math.sqrt(Xs * Xs + Ys * Ys);
	        double T0;
	        do
	        {
	            T0 = T;
	            double C = 1.0 / (1.0 + T0 * T0);
	            double N = c / Math.sqrt(1.0 + e * C);
	            h = Math.sqrt((Xs * Xs + Ys * Ys) / C) - N;
	            T = Zs * (1.0 + e) / Math.sqrt(Xs * Xs + Ys * Ys) / (1.0 + e * h / (N + h));
	          //  System.out.println("CURVE ITER...");
	        } while (Math.abs(T - T0) > 1.0e-5);
	        B = Math.atan(T);
	        if(Xs<0.0 )
	        L = -(Math.asin(Ys / Math.sqrt(Xs * Xs + Ys * Ys)))+Math.PI;
	        else
	        	L = Math.asin(Ys / Math.sqrt(Xs * Xs + Ys * Ys));	
	    }
	 public static void Cartesian(double B, double L, double h) {
	    	double e = (2.0 * f - f * f);
	    	double e_ = e / (1.0 - e);
	    	double N = a / Math.sqrt(1.0-e*e*Math.sin(B)*Math.sin(B));
	    	Xs = (N+h) * Math.cos(B)*Math.cos(L);
	    	Ys = (N+h) * Math.cos(B)*Math.sin(L);
	    	Zs = ((N/(1.0+e_))+h) * Math.sin(B);
	    	
	    }
	 // OVERLOADING Cartesian
	 public static void Cartesian() {
	    	double e = (2.0 * f - f * f);
	    	double e_ = e / (1.0 - e);
	    	double N = a / Math.sqrt(1.0-e*e*Math.sin(B)*Math.sin(B));
	    	Xs = (N+h) * Math.cos(B)*Math.cos(L);
	    	Ys = (N+h) * Math.cos(B)*Math.sin(L);
	    	Zs = ((N/(1.0+e_))+h) * Math.sin(B);
	    	
	    }
	 public static void Local_Geodetic(double DX, double DY, double DZ)
	    {
	        double n = -DX * Math.sin(B) * Math.cos(L) - DY * Math.sin(B) * Math.sin(L) + DZ * Math.cos(B);
	        double e = DY * Math.cos(L) - DX * Math.sin(L);
	        double u = DX * Math.cos(B) * Math.cos(L) + DY * Math.cos(B) * Math.sin(L) + DZ * Math.sin(B);

	        if(e>0.0 && n<0.0)
	        	Alfa = Math.atan(e / n)+Math.PI;
	        else if(e<0.0 && n<0.0)
	        	Alfa = Math.atan(e / n)+Math.PI;
	        else if(e<0.0 && n>0.0)
	        	Alfa = Math.atan(e / n)+2.0*Math.PI;
	        else
	        	Alfa = Math.atan(e / n);
	        
	        	Zr = Math.atan(Math.sqrt(n * n + e * e) / u);
	        
	        S = Math.sqrt(n * n + e * e + u * u);
	        System.out.println(Alfa*180.0/Math.PI+"  "+Zr*180.0/Math.PI+"  "+S);
	       // System.out.println("KUZEY=  "+(Zr*Math.cos(Alfa)*180.0/Math.PI)+"    DOGU=  "+(Zr*Math.sin(Alfa)*180.0/Math.PI));
	    }
	 public static void LocalToGeodetic(double alfa , double S ) {
			double n = Math.cos(alfa)*S;
			double e = Math.sin(alfa)*S;
			
			double DX = -(Math.sin(B)*Math.cos(L)*n + Math.sin(L)*e);
			double DY = Math.cos(L)*e - Math.sin(B)*Math.sin(L)*n;
			double DZ = Math.cos(B)*n;
			
			double X = Xs + DX;
			double Y = Ys + DY;
			double Z = Zs + DZ;
			Geodetic_Curve(X, Y, Z);

			
		}
	    public static void Referance_WGS84() { a = 6378137.0; f = 1.0 / 298.257222101; }
	    public static void Referance_BESSEL() { a = 6377397.155; f = 1.0 / 299.1528128; }
	    public static void Referance_HAYFORD() { a = 6378388.0; f = 1.0 / 297.0; }
}

