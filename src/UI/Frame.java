package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import DataAccess.ReadAlmanac;
import DataAccess.SatelliteNumber;

public class Frame extends JFrame {
	
	public JLabel clock = new JLabel();
	public DefaultTableModel model;
	public DefaultTableModel model2;
	public int height, widht;
	
	private JPanel Center = new JPanel();
	private JPanel North = new JPanel();
	private JPanel South = new JPanel();
	private JPanel East = new JPanel();
	
	private JFileChooser jfc;
	
	private String DosyaUzanti = "";
	
	private int Delay = 1000;
	private int sa = 0;

	private ReadAlmanac almanacData;
	private SatelliteNumber prn;
	private Drawing labelMap;
	
	public void Frames() {

		setBounds(50, 50, 1366, 768);
		
		almanacData = new ReadAlmanac();
		prn = new SatelliteNumber();
		
		North.setPreferredSize(new Dimension(0, 30));
		South.setPreferredSize(new Dimension(0, 200));
		East.setPreferredSize(new Dimension(250, 0));
		add(Center, BorderLayout.CENTER);
		add(North, BorderLayout.NORTH);
		add(East, BorderLayout.EAST);
		add(South, BorderLayout.SOUTH);

		JScrollPane scroll = new JScrollPane();
		labelMap = new Drawing(this);
		ImageIcon img = new ImageIcon(getClass().getResource("WorldMaprs.png"));
		labelMap.setIcon(img);
		scroll.setViewportView(labelMap);

		height = img.getIconHeight() - 50;
		widht = img.getIconWidth() - 50;
		JScrollPane IconScroll = new JScrollPane(labelMap, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Center.setLayout(new GridLayout(1, 1));
		Center.add(IconScroll);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void Lower(Object[][] text, String[] header) {

		model2 = new DefaultTableModel(text, header);
		JTable Center_Panel = new JTable();

		model2.setColumnCount(header.length);
		model2.setRowCount(text.length);
		Center_Panel.setModel(model2);
		
		for (int i = 0; i < header.length; i++)
			Center_Panel.getColumnModel().getColumn(i).setPreferredWidth(150);

		JScrollPane TextScroll = new JScrollPane(Center_Panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Center_Panel.getTableHeader().setResizingAllowed(false);
		Center_Panel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		South.setLayout(new GridLayout(1, 1));
		South.add(TextScroll);

	}

	public void Left(Object[][] text, String[] header) {

		model = new DefaultTableModel(text, header) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 1:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		JTable Center_Panel = new JTable();

		model.setColumnCount(header.length);
		model.setRowCount(text.length);
		Center_Panel.setModel(model);
		
		for (int i = 0; i < header.length; i++)
			Center_Panel.getColumnModel().getColumn(i).setPreferredWidth(123);

		JScrollPane TextScroll = new JScrollPane(Center_Panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Center_Panel.getTableHeader().setResizingAllowed(false);
		Center_Panel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		East.setLayout(new GridLayout(1, 1));
		East.add(TextScroll);

	}

	public void Buton() {
		JButton buton = new JButton("RUN");

		buton.setPreferredSize(new Dimension(100, 25));
		buton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String Satellite = "";
				for (int s = 0; s < 35; s++) 
				{
					if (s < 10)
						Satellite = "G0" + s;
					else
						Satellite = "G" + s;
					
					readSatellites(Satellite);
				}
				for (int s = 0; s < 35; s++) 
				{
					if (s < 10)
						Satellite = "R0" + s;
					else
						Satellite = "R" + s;
					
					readSatellites(Satellite);
				}
				for (int s = 0; s < 35; s++) 
				{
					if (s < 10)
						Satellite = "C0" + s;
					else
						Satellite = "C" + s;
					
					readSatellites(Satellite);
				}
				for (int s = 0; s < 50; s++) 
				{
					if (s < 10)
						Satellite = "E0" + s;
					else
						Satellite = "E" + s;
					
					readSatellites(Satellite);
				}
				buton.setEnabled(false);

			}
		});
		North.add(buton, BorderLayout.EAST);
		
		JButton reset = new JButton("RESET");
		
		reset.setPreferredSize(new Dimension(100, 25));
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buton.setEnabled(true);
				int size = model.getRowCount();
				for (int i = 0; i < size; i++) {
					model.removeRow(0);
					model2.removeRow(0);
					labelMap.DinamicTime = 0;
					labelMap.day = 0;
					labelMap.month = 0;
					labelMap.year = 0;
					labelMap.hn = 0;
					labelMap.O_C = true;
					labelMap.O_C2 = true;
				}

			}
		});
		North.add(reset, BorderLayout.WEST);
	}

	public void Chooser() {
		JButton buton = new JButton("...");

		buton.setPreferredSize(new Dimension(100, 25));
		buton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					DosyaUzanti = selectedFile.getAbsolutePath();
				}
			}
		});
		North.add(buton, BorderLayout.EAST);
		clock.setPreferredSize(new Dimension(250, 25));
		clock = new JLabel("00.00.00");
		North.add(clock, BorderLayout.EAST);
	}
	private void readSatellites(String Satellite)
	{
		almanacData.resetM();
		almanacData.toTLE(DosyaUzanti, prn.getSatellite(Satellite));
		
		if (almanacData.getM() != 0) 
		{
			double n = almanacData.getN() / 86400.0 * 2.0 * Math.PI;
			double a = Math.pow(398600.4418 / n / n, 1.0 / 3.0);
			model.addRow(new Object[] { Satellite, false });
			model2.addRow(new Object[] { Satellite, a, almanacData.getE(), almanacData.getM(), almanacData.getL0(), almanacData.getW0(), almanacData.getI0(), almanacData.getN(), almanacData.getN2(), almanacData.getN6(), almanacData.getT0(), almanacData.getWgps() });
		
		}
	}
}