import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Datas {
	 	public static double M = 0.0,e = 0.0, t0 = 0.0 ,l0 = 0.0, i0 = 0.0;
	    public static double n = 0.0, n2 = 0.0, n6 = 0.0, B = 0.0, w0 = 0.0,Wgps=0.0;
		private static BufferedReader buffer;
	    public static void toTLE(String uzanti, String uydu)
	    {
	        
	        try
	        {
	        	buffer = new BufferedReader(new FileReader(new File(uzanti)));
				String okuma =buffer.readLine();
	            while (okuma != null)
	            {

	                if (okuma.substring(2, 7).equalsIgnoreCase(uydu))
	                {
	                	
	                    Wgps = 2000.0+Double.valueOf(okuma.substring(18, 20));
	                    t0 = Double.valueOf(okuma.substring(20, 32));
	                    n2 = Double.valueOf(okuma.substring(33, 43));
	                    n6 = Double.valueOf(okuma.substring(45, 50) + "e" + okuma.substring(50, 52));
	                    B = Double.valueOf(okuma.substring(54, 59) + "e" + okuma.substring(59, 61));
	                    okuma = buffer.readLine();
	                    i0 = Double.valueOf(okuma.substring(8, 16));
	                    l0 = Double.valueOf(okuma.substring(17, 25));
	                    e = Double.valueOf("0." + okuma.substring(26, 33));
	                    w0 = Double.valueOf(okuma.substring(34, 42));
	                    M = Double.valueOf(okuma.substring(43, 51));
	                    n = Double.valueOf(okuma.substring(53, 63));
	                }


	                okuma = buffer.readLine();
	                
	            }
	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (StringIndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
			}
	        
	        

	    }
	    public static void ClearAll() {
	        i0 = 0;
            t0 = 0; l0 = 0;
            n2 = 0; e = 0;
            n6 = 0; w0 = 0;
            B = 0; M = 0;
            n = 0;
	    }
}
