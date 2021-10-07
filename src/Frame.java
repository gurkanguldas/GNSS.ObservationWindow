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
import javax.swing.table.DefaultTableModel;

public class Frame extends JFrame {
	JPanel Center = new JPanel();
	static int height , widht;
	static JPanel North = new JPanel();
	static JPanel South = new JPanel();
	static JPanel East = new JPanel();
	static JLabel clock= new JLabel();
	static DefaultTableModel model;
	static DefaultTableModel model2;
	JFileChooser jfc;
	static String DosyaUzanti = "";
	int Delay = 1000;
	int sa = 0;

	public void Frames() {
		
		setBounds(50, 50, 1366, 768);
		North.setPreferredSize(new Dimension(0, 30));
		South.setPreferredSize(new Dimension(0, 200));
		East.setPreferredSize(new Dimension(250, 0));
		add(Center, BorderLayout.CENTER);
		add(North, BorderLayout.NORTH);
		add(East, BorderLayout.EAST);
		add(South, BorderLayout.SOUTH);
		
		JScrollPane scroll = new JScrollPane();
		Drawing labelMap = new Drawing();
		ImageIcon img = new ImageIcon(getClass().getResource("WorldMaprs.png"));
		labelMap.setIcon(img);
		scroll.setViewportView(labelMap);

		height = img.getIconHeight()-50;
		widht = img.getIconWidth()-50;
		JScrollPane IconScroll = new JScrollPane(labelMap, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    Center.setLayout(new GridLayout(1,1));
		Center.add(IconScroll);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void Lower(Object[][] text, String[] header) {

		model2 = new DefaultTableModel(text, header);
		JTable Center_Panel = new JTable();

		model2.setColumnCount(header.length);
		model2.setRowCount(text.length);
		Center_Panel.setModel(model2);
		for (int i = 0; i < header.length; i++)
			Center_Panel.getColumnModel().getColumn(i).setPreferredWidth(150);

		JScrollPane TextScroll = new JScrollPane(Center_Panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Center_Panel.getTableHeader().setResizingAllowed(false);
		Center_Panel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		South.setLayout(new GridLayout(1, 1));
		South.add(TextScroll);

	}

	public static void Left(Object[][] text, String[] header) {

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

		JScrollPane TextScroll = new JScrollPane(Center_Panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
				for (int s = 0; s < 35; s++) {
					if (s < 10)
						Satellite = "G0" + s;
					else
						Satellite = "G" + s;
					Datas.ClearAll();
					Datas.toTLE(DosyaUzanti, Status.Number(Satellite));
					if (Datas.M != 0) {
						double n = Datas.n / 86400.0 * 2.0 * Math.PI;
						double a = Math.pow(398600.4418 / n / n, 1.0 / 3.0);
						model.addRow(new Object[] { Satellite, false });
						model2.addRow(new Object[] { Satellite, a, Datas.e, Datas.M, Datas.l0, Datas.w0, Datas.i0,
								Datas.n, Datas.n2, Datas.n6, Datas.t0 });
					}
				}
				for (int s = 0; s < 35; s++) {
					if (s < 10)
						Satellite = "R0" + s;
					else
						Satellite = "R" + s;
					Datas.ClearAll();
					Datas.toTLE(DosyaUzanti, Status.Number(Satellite));
					if (Datas.M != 0) {
						double n = Datas.n / 86400.0 * 2.0 * Math.PI;
						double a = Math.pow(398600.4418 / n / n, 1.0 / 3.0);
						model.addRow(new Object[] { Satellite, false });
						model2.addRow(new Object[] { Satellite, a, Datas.e, Datas.M, Datas.l0, Datas.w0, Datas.i0,
								Datas.n, Datas.n2, Datas.n6, Datas.t0 });
					}
				}
				for (int s = 0; s < 35; s++) {
					if (s < 10)
						Satellite = "C0" + s;
					else
						Satellite = "C" + s;
					Datas.ClearAll();
					Datas.toTLE(DosyaUzanti, Status.Number(Satellite));
					if (Datas.M != 0) {
						double n = Datas.n / 86400.0 * 2.0 * Math.PI;
						double a = Math.pow(398600.4418 / n / n, 1.0 / 3.0);
						model.addRow(new Object[] { Satellite, false });
						model2.addRow(new Object[] { Satellite, a, Datas.e, Datas.M, Datas.l0, Datas.w0, Datas.i0,
								Datas.n, Datas.n2, Datas.n6, Datas.t0 });
					}
				}
				for (int s = 0; s < 50; s++) {
					if (s < 10)
						Satellite = "E0" + s;
					else
						Satellite = "E" + s;
					Datas.ClearAll();
					Datas.toTLE(DosyaUzanti, Status.Number(Satellite));
					if (Datas.M != 0) {
						double n = Datas.n / 86400.0 * 2.0 * Math.PI;
						double a = Math.pow(398600.4418 / n / n, 1.0 / 3.0);
						model.addRow(new Object[] { Satellite, false });
						model2.addRow(new Object[] { Satellite, a, Datas.e, Datas.M, Datas.l0, Datas.w0, Datas.i0,
								Datas.n, Datas.n2, Datas.n6, Datas.t0 });
					}
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
					Drawing.DinamicTime=0;
					Drawing.day=0;
					Drawing.month=0;
					Drawing.year=0;
					Drawing.hn=0;
					Drawing.O_C=true;
					Drawing.O_C2=true;
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
		clock.setPreferredSize(new Dimension(250,25));
		clock = new JLabel("00.00.00");
		North.add(clock, BorderLayout.EAST);
	}
}