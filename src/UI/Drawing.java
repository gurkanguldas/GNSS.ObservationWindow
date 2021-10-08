package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import DataAccess.SatelliteCoordinate;

public class Drawing extends JLabel implements ActionListener {

	public double DinamicTime = 0.0;
	
	public Boolean O_C = true, 
				   O_C2 = true;
	
	public int day = 0, 
			   month = 0, 
			   year = 0,
			   hn = 0;
	
	public int x[];
	public int y[];

	private SatelliteCoordinate satellite;
	private DrawControl drawControl;
	private DrawingPoint drawingPoint;
	private Timer animator;
	
	private Frame frame;
	
	public Drawing(Frame frame) 
	{
		super();
		this.frame = frame;
		this.drawControl = new DrawControl(this);
		animator = new Timer(50, this);
		animator.start();

	}
	
	public void paint(Graphics g) 
	{

		super.paintComponent(g);

		for (int i = 0; i < frame.model.getRowCount(); i++) {

			if ((Boolean) frame.model.getValueAt(i, 1)) {

				this.satellite = new SatelliteCoordinate(frame.model2, i);

				if (O_C2) 
				{
					this.satellite.getTk2T0();
					O_C2 = false;
				}

				Boolean kontrol_x1 = false, kontrol_x2 = false, kontrol_x3 = false, Cy = false;

				x = new int[360];
				y = new int[360];

				drawingPoint = new DrawingPoint(satellite,frame);
				
				drawingPoint.satellite(DinamicTime / 240.0);
				g.setColor(new Color(0, 0, 0));
				g.fillOval(drawingPoint.xS, drawingPoint.yS, 10, 10);

				if (drawingPoint.yS < 350)
					Cy = true;
				else
					Cy = false;

				for (int j = 0; j < 360; j++) {
					drawingPoint.DALF = j;

					drawingPoint.window(DinamicTime / 240.0);
					x[j] = drawingPoint.x;
					y[j] = drawingPoint.y;

					if (x[j] < 150)
						kontrol_x1 = true;
					if (x[j] > 800 && x[j] < 900)
						kontrol_x2 = true;
					if (x[j] > 1400)
						kontrol_x3 = true;

				}
				
				if (satellite.getSatellite().substring(0, 1).equalsIgnoreCase("R"))
					g.setColor(new Color(0, 0, 255, 127));
				else if (satellite.getSatellite().substring(0, 1).equalsIgnoreCase("G"))
					g.setColor(new Color(255, 0, 0, 127));
				else if (satellite.getSatellite().substring(0, 1).equalsIgnoreCase("C"))
					g.setColor(new Color(0, 255, 0, 127));
				else if (satellite.getSatellite().substring(0, 1).equalsIgnoreCase("E"))
					g.setColor(new Color(255, 255, 0, 127));
				else
					g.setColor(new Color(255, 0, 255, 127));
				
				if (kontrol_x1 && kontrol_x2 && kontrol_x3) {
					drawControl.Up(Cy);
					g.fillPolygon(drawControl.Modified_x, drawControl.Modified_y, 362);
					drawControl.Kill();

				} else {
					drawControl.Left();
					g.fillPolygon(x, y, 360 - drawControl.j);
					g.fillPolygon(drawControl.Modified_x, drawControl.Modified_y, drawControl.j);
					drawControl.Kill();
				}

				if (O_C == true) {
					DinamicTime++;
					double real = satellite.getTk();
					int hs = (int) ((real - (int) (real)) * 24.0);
					int ms = (int) (((real - (int) (real)) * 24.0 - hs) * 60.0);
					int ss = (int) (((real - (int) (real)) * 24.0 - hs - ms / 60.0) * 3600.0);

					int h = (int) (DinamicTime / 240.0);
					int m = (int) (((DinamicTime / 240.0) - h) * 60.0);
					int s = (int) (((DinamicTime / 240.0) - h - m / 60.0) * 3600.0);
					h += hs - hn * 24;
					m += ms;
					s += ss;
					if (s > 59) {
						m++;
						s -= 60;
					}
					if (m > 59) {
						h++;
						m -= 60;
					}
					if (hn == 0) {
						day = satellite.getTime().getDay();
						month = satellite.getTime().getMonth();
						year = satellite.getTime().getYear();
					}

					if (h > 23) {
						day++;
						hn++;
					}
					if (day > satellite.getTime().MaxDay(month, year)) {
						day = 1;
						month++;
					}
					if (month > 11) {
						month = 1;
						year++;
					}
					frame.clock.setText(day + "/" + month + "/" + year + "  " + h + ":" + m + ":" + s);

					O_C = false;
				}

			}
		}
		O_C = true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		repaint();
	}
}