import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

public class Drawing extends JLabel implements ActionListener {
	
	static double DinamicTime = 0.0 ;
	static Boolean O_C=true,O_C2=true;
	static int day=0,month=0,year=0,hn=0;
	public Timer animator;
	static int x[];
	static int y[];
	Drawing() {
		super();
		animator = new Timer(50, this);
		animator.start();
		
	}
/********************************************************************************************************************************//*
   * Panel Background
***********************************************************************************************************************************/
    public void paint(Graphics g) {

    		super.paintComponent(g);
    		
		for (int i = 0; i < Frame.model.getRowCount(); i++) {
				
			if((Boolean)Frame.model.getValueAt(i, 1)) {
				
					Values.e = (double)Frame.model2.getValueAt(i, 2);
					Values.M = (double)Frame.model2.getValueAt(i, 3);
					Values.l = (double)Frame.model2.getValueAt(i, 4);
					Values.w = (double)Frame.model2.getValueAt(i, 5);
					Values.ik = (double)Frame.model2.getValueAt(i, 6);
					Values.n  = (double)Frame.model2.getValueAt(i, 7);
					Values.n2 = (double)Frame.model2.getValueAt(i, 8);
					Values.n6 = (double)Frame.model2.getValueAt(i, 9);
					Values.t0 = (double)Frame.model2.getValueAt(i, 10);
					if(O_C2) {Values.tk=Values.t0;O_C2=false;}
						
					Values.satalite = (String)Frame.model2.getValueAt(i, 0);
					
					Boolean kontrol_x1=false,
							kontrol_x2=false,
							kontrol_x3=false,
							Cy=false;
					
					 x=new int[360];
					 y=new int[360];
					
					DrawingPoint.satellite(DinamicTime/240.0);
					g.setColor(new Color(0, 0, 0));
					g.fillOval(DrawingPoint.xS, DrawingPoint.yS, 10, 10);
					
					 if(DrawingPoint.yS<350)
						 Cy=true;
					 else
						 Cy=false;
					 
					for (int j = 0; j < 360; j++) {
						 DrawingPoint.DALF=j;
						 
						 DrawingPoint.window(DinamicTime/240.0);
						 x[j]=DrawingPoint.x;
						 y[j]=DrawingPoint.y;
						 
						 if(x[j]<150)
							 kontrol_x1=true;
						 if(x[j]>800 && x[j]<900) 
							 kontrol_x2=true;
						 if(x[j]>1400)
							 kontrol_x3=true;

							
					}
					if(Values.satalite.substring(0,1).equalsIgnoreCase("R"))
						g.setColor(new Color(0, 0, 255, 127));
					else if(Values.satalite.substring(0,1).equalsIgnoreCase("G"))
						g.setColor(new Color(255, 0, 0, 127));
					else if(Values.satalite.substring(0,1).equalsIgnoreCase("C"))
						g.setColor(new Color(0, 255, 0, 127));
					else if(Values.satalite.substring(0,1).equalsIgnoreCase("E"))
						g.setColor(new Color(255, 255, 0, 127));
					else
						g.setColor(new Color(255, 0, 255, 127));
					if(kontrol_x1 && kontrol_x2 && kontrol_x3) {
						DrawControl.Up(Cy);
						g.fillPolygon(DrawControl.Modified_x, DrawControl.Modified_y,362);
						DrawControl.Kill();
						
					}
					else{
						DrawControl.Left();
						g.fillPolygon(x, y,360-DrawControl.j);
						g.fillPolygon(DrawControl.Modified_x, DrawControl.Modified_y, DrawControl.j);
						DrawControl.Kill();
					}
					

					
					
					if(O_C==true) {
					DinamicTime++;
					double real = Values.tk;
					int hs = (int)((real-(int)(real))*24.0);
					int ms = (int)(((real-(int)(real))*24.0-hs)*60.0);
					int ss = (int)(((real-(int)(real))*24.0-hs-ms/60.0)*3600.0);

					int h = (int)(DinamicTime/240.0);
					int m = (int)(((DinamicTime/240.0)-h)*60.0);
					int s = (int)(((DinamicTime/240.0)-h-m/60.0)*3600.0);
					h+=hs-hn*24;
					m+=ms;
					s+=ss;
					if(s>59) {m++; s-=60;}
					if(m>59) {h++; m-=60;}
					if(hn==0) {
					 day = Values.time.day;
					 month = Values.time.month;
					 year = Values.time.year;}


					if(h>23) {day++; hn++;}
					if(day>UTC.MaxDay(month, year)) {day=1;month++;}
					if(month>11) {month=1;year++;}
					Frame.clock.setText(day+"/"+month+"/"+year+"  "+h+":"+m+":"+s);

					O_C=false;}
					
			}}O_C=true;

    		//Image img = new ImageIcon("WorldMap.png").getImage();
    		//Image newImage = new ImageIcon(
			//img.getScaledInstance(this.getSize().width, this.getSize().height, Image.SCALE_SMOOTH)).getImage();
			//g.drawImage(newImage, 0, 0, null);
/**********************************************************************************************************************************/
/**********************************************************************************************************************************/
	 
	}


    	@Override
    	public void actionPerformed(ActionEvent arg0) {
    		repaint();
    		
    	}
}