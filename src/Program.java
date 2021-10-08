import UI.Frame;

public class Program {
	public static void main(String[] args) 
	{

		Frame frame = new Frame();

		frame.Lower(new Object[][] {}, new String[]   {"PRN", "a[km]", "e[]", "M[o]", "l[o]", "w[o]", "i[o]","n[rev/day]", "n2[rev/day^2]", "n6[rev/day^3]", "t0", "Wgps", "" });
		frame.Left(new Object[][] {}, new String[] { "PRN", "Select" });
		
		frame.Buton();
		frame.Chooser();
		frame.Frames();

	}
}
