import javax.swing.text.Position;

public class DrawingPoint {
	static int x , y ;
	static int xS ,yS;
	static double DALF = 0.0;
	public static void window(double DinamicTime){
		Values.TLE(DinamicTime);
		PointTransform.Referance_WGS84();
		
		PointTransform.Geodetic_Curve(Values.X, Values.Y, Values.Z);
		PointTransform.Cartesian();
		PointTransform.LocalToGeodetic(DALF/180.0*Math.PI, 2.5e7);

		x =(int)(((PointTransform.L/2.0/Math.PI*Frame.widht)+Frame.widht/2.0)%Frame.widht)+25;
		y =(int)((Math.PI/2.0-PointTransform.B)/Math.PI*Frame.height)+25;
	}
	public static void satellite(double DinamicTime) {
		Values.TLE(DinamicTime);
		PointTransform.Referance_WGS84();
		PointTransform.Geodetic_Curve(Values.X, Values.Y, Values.Z);
		
		xS =(int)(((PointTransform.L/2.0/Math.PI*Frame.widht)+Frame.widht/2.0)%Frame.widht)+25;
		yS =(int)((Math.PI/2.0-PointTransform.B)/Math.PI*Frame.height)+25;
	}
}
