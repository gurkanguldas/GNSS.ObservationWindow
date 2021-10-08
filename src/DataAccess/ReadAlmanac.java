package DataAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadAlmanac {

	private double M    = 0.0, 
				   e    = 0.0, 
				   t0   = 0.0, 
				   l0   = 0.0, 
				   i0   = 0.0, 
				   n    = 0.0, 
				   n2   = 0.0, 
				   n6   = 0.0, 
				   B    = 0.0, 
				   w0   = 0.0,
				   Wgps = 0.0;

	private BufferedReader buffer;

	public void toTLE(String url, String satellite) 
	{
		try 
		{
			buffer = new BufferedReader(new FileReader(new File(url)));
			String read = buffer.readLine();

			while (read != null) {
				
				if (read.length() > 0)
					if (read.substring(2, 7).equalsIgnoreCase(satellite)) 
					{
						Wgps = 2000.0 + Double.valueOf(read.substring(18, 20));
						t0 = Double.valueOf(read.substring(20, 32));
						n2 = Double.valueOf(read.substring(33, 43));
						n6 = Double.valueOf(read.substring(45, 50) + "e" + read.substring(50, 52));
						B = Double.valueOf(read.substring(54, 59) + "e" + read.substring(59, 61));

						read = buffer.readLine();

						i0 = Double.valueOf(read.substring(8, 16));
						l0 = Double.valueOf(read.substring(17, 25));
						e = Double.valueOf("0." + read.substring(26, 33));
						w0 = Double.valueOf(read.substring(34, 42));
						M = Double.valueOf(read.substring(43, 51));
						n = Double.valueOf(read.substring(53, 63));
					}
				read = buffer.readLine();
			}
		} 
		catch (IOException e) 
		{
			
		}
	}

	public double getM() 
	{
		return M;
	}

	public double getE() 
	{
		return e;
	}

	public double getT0() 
	{
		return t0;
	}

	public double getL0() 
	{
		return l0;
	}

	public double getI0() 
	{
		return i0;
	}

	public double getN() 
	{
		return n;
	}

	public double getN2() 
	{
		return n2;
	}

	public double getN6() 
	{
		return n6;
	}

	public double getB() 
	{
		return B;
	}

	public double getW0() 
	{
		return w0;
	}

	public double getWgps() 
	{
		return Wgps;
	}
	
	public void resetM()
	{
		M = 0;
	}
}
