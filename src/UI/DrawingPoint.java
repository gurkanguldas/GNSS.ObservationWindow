package UI;

import DataAccess.PointTransform;
import DataAccess.SatelliteCoordinate;

public class DrawingPoint {
	public int x , y ;
	public int xS ,yS;
	public double DALF = 0.0;
	
	private SatelliteCoordinate satellite;
	private Frame frame;
	
	public DrawingPoint(SatelliteCoordinate satellite, Frame frame)
	{
		this.frame = frame;
		this.satellite = satellite;
	}
	
	public void window(double DinamicTime)
	{
		satellite.TLE(DinamicTime);
		PointTransform.Referance_WGS84();
		
		PointTransform.Geodetic_Curve(satellite.getX(), satellite.getY(), satellite.getZ());
		PointTransform.Cartesian();
		PointTransform.LocalToGeodetic(DALF/180.0*Math.PI, 2.5e7);

		x =(int)(((PointTransform.L/2.0/Math.PI*frame.widht)+frame.widht/2.0)%frame.widht)+25;
		y =(int)((Math.PI/2.0-PointTransform.B)/Math.PI*frame.height)+25;
	}
	public void satellite(double DinamicTime) 
	{
		satellite.TLE(DinamicTime);
		PointTransform.Referance_WGS84();
		PointTransform.Geodetic_Curve(satellite.getX(), satellite.getY(), satellite.getZ());
		
		xS =(int)(((PointTransform.L/2.0/Math.PI*frame.widht)+frame.widht/2.0)%frame.widht)+25;
		yS =(int)((Math.PI/2.0-PointTransform.B)/Math.PI*frame.height)+25;
	}
}
