import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
*/
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;

import com.standbysoft.component.date.event.*;
import com.standbysoft.component.date.swing.*;

//import com.mysql.jdbc.*;

public class WeatherGraph extends JApplet implements ActionListener{
	private static final long serialVersionUID = 1L;

	JPanel mainPanel;
	JPanel chartContainer;
	String temps[];
	String times[];
	
	Choice city;
	Choice interval;
	DateListener startListener;
	DateListener endListener;
	
	Button genButton;
	Button backButton;	
	Button nextButton;

	JPanel tablePanel;
	JScrollPane tableContainer;
	
	String chartTimes[][];
	String chartTemps[][];
	
	int page = 0;
	
	public void init(){
/*		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://sql301.byethost7.com:3306/b7_14226855_weather","b7_14226855", "mm3612");
			//PreparedStatement pst = con.prepareStatement("create table test (testint int);");
			//pst.execute();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JFrame frame = new JFrame("Error");
			JOptionPane.showMessageDialog(frame, e);
		}
*/

		//String param = getParameter("temps");
		String param = "274.57;275.74;276.02;275.75;274.53;274.46;274.8;274.38;272.94;272.35;272.69;271.76;272.14;272.13;272.8;273.62;275.84;277.3;279.53;280.62;281.52;281.84;281.82;282.42;282.39;282.63;282.9;282.74;282.92;282.21;281.9;281.59;281.31;281.41;281.28;281.21;280.87;280.49;280.15;280.12;279.89;280.35;279.81;279.7;279.45;279.41;279.54;279.36;276.93;279.03;279.03;279.69;278.1;279.29;277.92;279.34;277.87;279.32;279.15;279.99;279.35;280.1;280.09;280.67;280.91;281.55;281.31;281.64;281.16;281.34;281.33;281.33;281.41;281.35;281.12;280.75;280.56;280.54;280.65;280.14;279.15;280.14;280.74;280.15;280.74;278.15;279.57;275.65;277.76;277.68;277.82;277.02;276.9;276.58;276.55;275.73;275.44;275.44;274.89;274.67;274.12;273.85;273.6;273.59;273.44;273.44;273.76;273.86;274.17;274.96;275.47;275.72;276.52;277.34;277.68;278.19;278.86;279.43;280.22;280.14;280.11;279.89;279.88;279.15;278.68;277.98;277.96;277.72;277.49;277.13;277.12;277.14;277.11;277.12;276.97;277.1;277.1;277.2;277.28;277.1;277.21;277.25;277.22;277.33;277.07;277.03;277;276.92;276.74;276.75;276.24;276.25;276.23;276.01;276.39;276.44;276.65;276.92;277.18;277.15;277.25;277.09;277.03;276.54;276.45;276.24;276.03;276.13;276.22;275.93;275.156;272.539;272.539;272.539;272.539;270.376;272.539;270.376;268.005;268.005;268.005;269.422;268.404;267.021;268.182;267.021;268.182;267.021;267.021;267.683;267.683;267.683;267.683;273.266;272.857;272.857;273.266;276.98;276.98;277.143;276.962;277.143;272;272;272;272;272;272.031;271.154;271.154;272.031;271.484;270.662;270.662;272.162;272.162;272.162;272.162;271.784;271.784;271.784;271.784;275.984;275.984;275.984;277.452;276.137;277.452;277.452;275.124;273.521;270.949;272.4;270.949;270.141;268.956;268.956;268.956;270.141;270.141;268.518;267.401;267.401;267.401;268.518;266.91;266.91;266.91;266.91;267.66;267.66;266.956;267.601;269.37;269.1;268.78;268.64;268.63;269;269.44;269.88;270.27;271;271.41;272.01;272.54;273.35;274.06;274.54;274.67;274.62;274.85;274.99;274.91;274.19;274.28;274.49;274.63;274.17;274.11;274.77;275.06;275.05;275.06;275.1;275.19;275.16;275.37;275.08;274.92;274.91;274.9;274.86;275.28;275.27;275.27;274.97;275.18;275.01;275.09;275.15;275.29;275.29;275.46;276.12;276.3;276.62;276.9;277.17";
		temps = param.split(";");
		param = "2014-01-13 00:58:08;2014-01-12 23:28:05;2014-01-12 22:28:06;2014-01-12 22:58:08;2014-01-13 01:28:06;2014-01-13 01:58:09;2014-01-13 02:28:07;2014-01-13 02:58:08;2014-01-13 04:28:08;2014-01-13 04:58:10;2014-01-13 05:28:08;2014-01-13 05:58:11;2014-01-13 06:58:14;2014-01-13 07:28:09;2014-01-13 08:28:09;2014-01-13 08:58:10;2014-01-13 09:28:09;2014-01-13 09:58:09;2014-01-13 10:58:09;2014-01-13 11:28:07;2014-01-13 11:58:04;2014-01-13 12:28:06;2014-01-13 12:58:08;2014-01-13 13:58:08;2014-01-13 14:28:06;2014-01-13 14:58:07;2014-01-13 15:28:12;2014-01-13 15:58:07;2014-01-13 16:28:07;2014-01-13 16:58:07;2014-01-13 17:28:08;2014-01-13 17:58:07;2014-01-13 18:28:14;2014-01-13 19:28:09;2014-01-13 19:58:09;2014-01-13 20:28:07;2014-01-13 20:58:08;2014-01-13 21:28:04;2014-01-13 21:58:09;2014-01-13 22:28:04;2014-01-13 22:58:09;2014-01-13 23:28:10;2014-01-13 23:58:12;2014-01-14 00:28:11;2014-01-14 00:58:13;2014-01-14 01:28:07;2014-01-14 01:58:11;2014-01-14 02:28:07;2014-01-14 02:58:08;2014-01-14 03:28:08;2014-01-14 03:58:09;2014-01-14 04:28:10;2014-01-14 04:58:12;2014-01-14 05:28:18;2014-01-14 05:58:07;2014-01-14 06:28:06;2014-01-14 06:58:08;2014-01-14 07:28:07;2014-01-14 07:58:07;2014-01-14 08:28:05;2014-01-14 08:58:08;2014-01-14 09:28:06;2014-01-14 09:58:07;2014-01-14 10:28:06;2014-01-14 10:58:09;2014-01-14 11:28:07;2014-01-14 11:58:08;2014-01-14 12:28:07;2014-01-14 12:58:10;2014-01-14 13:28:07;2014-01-14 13:58:15;2014-01-14 14:28:09;2014-01-14 14:58:09;2014-01-14 15:28:10;2014-01-14 15:58:08;2014-01-14 16:28:10;2014-01-14 16:58:08;2014-01-14 17:28:08;2014-01-14 17:58:12;2014-01-14 18:28:06;2014-01-14 18:58:15;2014-01-14 19:28:08;2014-01-14 20:28:06;2014-01-14 20:58:07;2014-01-14 21:28:06;2014-01-14 21:58:06;2014-01-14 22:28:05;2014-01-14 22:58:12;2014-01-14 23:28:07;2014-01-14 23:58:10;2014-01-15 00:28:06;2014-01-15 00:58:08;2014-01-15 01:28:05;2014-01-15 01:58:07;2014-01-15 02:28:05;2014-01-15 02:58:07;2014-01-15 03:28:09;2014-01-15 03:58:11;2014-01-15 04:28:09;2014-01-15 04:58:10;2014-01-15 05:28:07;2014-01-15 05:58:12;2014-01-15 06:28:07;2014-01-15 06:58:12;2014-01-15 07:28:08;2014-01-15 07:58:09;2014-01-15 08:28:07;2014-01-15 08:58:09;2014-01-15 09:28:08;2014-01-15 09:58:09;2014-01-15 10:28:10;2014-01-15 10:58:09;2014-01-15 11:28:07;2014-01-15 11:58:07;2014-01-15 12:28:09;2014-01-15 12:58:07;2014-01-15 13:28:07;2014-01-15 13:58:09;2014-01-15 14:28:07;2014-01-15 14:58:09;2014-01-15 15:28:08;2014-01-15 15:58:07;2014-01-15 16:28:07;2014-01-15 16:58:09;2014-01-15 17:28:08;2014-01-15 17:58:08;2014-01-15 18:28:07;2014-01-15 18:58:09;2014-01-15 19:28:09;2014-01-15 19:58:09;2014-01-15 20:58:10;2014-01-15 21:28:07;2014-01-15 21:58:07;2014-01-15 22:28:06;2014-01-15 22:58:08;2014-01-15 23:28:06;2014-01-15 23:58:10;2014-01-16 00:28:11;2014-01-16 00:58:12;2014-01-16 01:28:09;2014-01-16 01:58:09;2014-01-16 02:28:07;2014-01-16 02:58:08;2014-01-16 03:28:09;2014-01-16 03:58:21;2014-01-16 04:28:10;2014-01-16 04:58:08;2014-01-16 05:28:08;2014-01-16 05:58:14;2014-01-16 06:28:05;2014-01-16 07:58:11;2014-01-16 08:58:08;2014-01-16 10:28:07;2014-01-16 10:58:11;2014-01-16 11:28:19;2014-01-16 11:58:08;2014-01-16 12:28:08;2014-01-16 12:58:08;2014-01-16 13:28:09;2014-01-16 13:58:08;2014-01-16 14:28:06;2014-01-16 14:58:09;2014-01-16 15:28:08;2014-01-16 16:28:06;2014-01-16 16:58:14;2014-01-16 17:28:09;2014-01-16 17:58:11;2014-01-16 18:28:11;2014-01-16 18:58:12;2014-01-16 19:28:16;2014-01-16 19:58:13;2014-01-16 20:28:09;2014-01-16 20:58:08;2014-01-16 21:28:09;2014-01-16 21:58:10;2014-01-16 22:28:09;2014-01-16 22:58:08;2014-01-16 23:28:07;2014-01-16 23:58:10;2014-01-17 00:28:09;2014-01-17 00:58:10;2014-01-17 01:28:09;2014-01-17 01:58:08;2014-01-17 02:28:10;2014-01-17 02:58:09;2014-01-17 03:28:11;2014-01-17 03:58:04;2014-01-17 04:28:07;2014-01-17 04:58:19;2014-01-17 05:58:13;2014-01-17 06:58:08;2014-01-17 07:28:08;2014-01-17 07:58:07;2014-01-17 08:58:10;2014-01-17 10:28:10;2014-01-17 10:58:12;2014-01-17 11:28:09;2014-01-17 12:58:07;2014-01-17 13:28:07;2014-01-17 14:28:08;2014-01-17 14:58:07;2014-01-17 15:58:15;2014-01-17 17:28:08;2014-01-17 17:58:06;2014-01-17 18:28:12;2014-01-17 18:58:13;2014-01-17 19:58:09;2014-01-17 20:28:06;2014-01-17 21:58:12;2014-01-17 22:28:08;2014-01-17 22:58:13;2014-01-17 23:58:14;2014-01-18 01:28:07;2014-01-18 01:58:09;2014-01-18 02:58:11;2014-01-18 04:28:11;2014-01-18 04:58:11;2014-01-18 05:28:09;2014-01-18 05:58:10;2014-01-18 06:58:11;2014-01-18 07:28:09;2014-01-18 07:58:08;2014-01-18 08:28:09;2014-01-18 09:28:10;2014-01-18 11:28:13;2014-01-18 11:58:12;2014-01-18 12:28:08;2014-01-18 12:58:10;2014-01-18 13:28:12;2014-01-18 14:28:12;2014-01-18 16:28:09;2014-01-18 17:58:09;2014-01-18 19:28:09;2014-01-18 20:28:08;2014-01-18 20:58:08;2014-01-18 21:28:07;2014-01-18 21:58:08;2014-01-18 22:28:06;2014-01-18 22:58:11;2014-01-18 23:28:08;2014-01-18 23:58:14;2014-01-19 00:28:07;2014-01-19 00:58:08;2014-01-19 01:28:13;2014-01-19 01:58:12;2014-01-19 02:28:05;2014-01-19 02:58:09;2014-01-19 03:28:10;2014-01-19 03:58:13;2014-01-19 04:58:08;2014-01-19 05:28:12;2014-01-19 05:58:07;2014-01-19 06:28:14;2014-01-19 06:58:10;2014-01-19 07:28:13;2014-01-19 07:58:10;2014-01-19 08:28:06;2014-01-19 08:58:08;2014-01-19 09:28:08;2014-01-19 09:58:08;2014-01-19 10:28:10;2014-01-19 10:58:08;2014-01-19 11:28:09;2014-01-19 11:58:09;2014-01-19 12:58:08;2014-01-19 13:28:09;2014-01-19 13:58:08;2014-01-19 14:28:08;2014-01-19 14:58:09;2014-01-19 15:28:10;2014-01-19 15:58:10;2014-01-19 16:28:08;2014-01-19 16:58:10;2014-01-19 17:28:10;2014-01-19 17:58:09;2014-01-19 18:28:09;2014-01-19 18:58:17;2014-01-19 19:28:10;2014-01-19 19:58:10;2014-01-19 20:28:10;2014-01-19 20:58:08;2014-01-19 21:28:09;2014-01-19 21:58:11;2014-01-19 22:28:07;2014-01-19 22:58:18;2014-01-19 23:28:11;2014-01-19 23:58:18;2014-01-20 00:28:13;2014-01-20 00:58:12;2014-01-20 01:28:10;2014-01-20 01:58:10;2014-01-20 02:28:11;2014-01-20 02:58:13;2014-01-20 03:28:13;2014-01-20 03:58:12;2014-01-20 04:28:11;2014-01-20 04:58:11;2014-01-20 05:28:10;2014-01-20 05:58:10;2014-01-20 06:28:14;2014-01-20 06:58:13;2014-01-20 07:28:26;2014-01-20 07:58:09;2014-01-20 08:28:22;2014-01-20 09:28:13;2014-01-20 09:58:13;2014-01-20 10:28:12;2014-01-20 10:58:11;2014-01-20 11:28:10";
		//param = getParameter("times");
		times = param.split(";");
		
		setSize(1000, 700);
		mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setSize(1000, 700);
		mainPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		GridBagLayout mainGrid = new GridBagLayout();
		GridBagConstraints mainc = new GridBagConstraints();
		mainPanel.setLayout(mainGrid);
		mainc.gridwidth = GridBagConstraints.REMAINDER;
		
		JButton rawTempButton = new JButton("Temperature history");
		rawTempButton.addActionListener(this);
		mainGrid.setConstraints(rawTempButton, mainc);
		mainPanel.add(rawTempButton);
		
		JButton statsButton = new JButton("Daily Statistics");
		statsButton.addActionListener(this);
		mainGrid.setConstraints(statsButton, mainc);
		mainPanel.add(statsButton);
		
		/*
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.WHITE);
		menuPanel.setPreferredSize(new Dimension(210,300));
		
		mainc.fill = GridBagConstraints.NONE;
		mainc.insets = new Insets(5, 5, 5, 5);
		JPanel menuContainer = new JPanel();
		menuContainer.add(menuPanel);
		mainGrid.setConstraints(menuContainer, mainc);
		mainPanel.add(menuContainer);
		
		
		
		DefaultCategoryDataset test = new DefaultCategoryDataset();
		JFreeChart chart = ChartFactory.createLineChart("", "Time (Military)", "Temperature (°C)", test, PlotOrientation.VERTICAL, false, true, false);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(740, 300));

		mainc.gridwidth = GridBagConstraints.REMAINDER;
		chartContainer = new JPanel();
		chartContainer.add(chartPanel);
		mainGrid.setConstraints(chartContainer, mainc);
		mainPanel.add(chartContainer);
		
		tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(220,350));
		mainc.gridwidth = 1;
		mainGrid.setConstraints(tablePanel, mainc);
		mainPanel.add(tablePanel);
		
		Object[][] data = new Object[0][2];
		
		String[] columnNames = {"Time (Military)","Temperature (°C)"};
		
		JTable table = new JTable(data, columnNames){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		
		tableContainer = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableContainer.setPreferredSize(new Dimension(210,340));
		tablePanel.add(tableContainer);
		
		JPanel switchPanel = new JPanel();
		switchPanel.setBackground(Color.WHITE);
		mainc.gridwidth = GridBagConstraints.REMAINDER;
		mainc.anchor = GridBagConstraints.NORTH;
		mainGrid.setConstraints(switchPanel, mainc);
		mainPanel.add(switchPanel);
		
		backButton = new Button("Previous");
		backButton.addActionListener(this);
		backButton.setEnabled(false);
		backButton.setPreferredSize(new Dimension(60, 25));
		nextButton = new Button("Next");
		nextButton.addActionListener(this);
		nextButton.setEnabled(false);
		nextButton.setPreferredSize(new Dimension(60, 25));
		switchPanel.add(backButton);
		switchPanel.add(nextButton);
		
		GridBagLayout menuGrid = new GridBagLayout();
		GridBagConstraints menuc = new GridBagConstraints();
		menuPanel.setLayout(menuGrid);
		menuc.fill = GridBagConstraints.HORIZONTAL;

		menuc.weighty=0.5;
		menuc.weightx=1;
		menuc.gridwidth=GridBagConstraints.REMAINDER;
		menuc.insets = new Insets(5, 5, 5, 5);
		
		JPanel cityPanel = new JPanel();
		GridLayout cityLayout = new GridLayout(0,1);
		cityPanel.setLayout(cityLayout);
		cityPanel.setBackground(Color.WHITE);
		JLabel cityLabel = new JLabel("Location");
		cityPanel.add(cityLabel);
		city = new Choice();
		city.addItem("New York");
		cityPanel.add(city);
		menuGrid.setConstraints(cityPanel, menuc);
		menuPanel.add(cityPanel);
		
		JPanel intervalPanel = new JPanel();
		GridLayout intervalLayout = new GridLayout(0,1);
		intervalPanel.setLayout(intervalLayout);
		intervalPanel.setBackground(Color.WHITE);
		JLabel intervalLabel = new JLabel("Interval");
		intervalPanel.add(intervalLabel);
		interval = new Choice();
		interval.addItem("Hourly");
		interval.addItem("3 Hours");
		interval.addItem("6 Hours");
		interval.addItem("Daily");
		intervalPanel.add(interval);
		menuGrid.setConstraints(intervalPanel, menuc);
		menuPanel.add(intervalPanel);
		
		JPanel startPanel = new JPanel();
		GridLayout startLayout = new GridLayout(0,1);
		startPanel.setLayout(startLayout);
		startPanel.setBackground(Color.WHITE);
		JLabel startLabel = new JLabel("Start Date");
		startPanel.add(startLabel);
		JDatePicker startDatePicker = new JDatePicker(true);
		startListener = new DateListener();
		startDatePicker.addDateSelectionListener(startListener);
		startListener.selectedDate = new Date();
		startPanel.add(startDatePicker);
		menuGrid.setConstraints(startPanel, menuc);
		menuPanel.add(startPanel);
		
		JPanel endPanel = new JPanel();
		GridLayout endLayout = new GridLayout(0,1);
		endPanel.setLayout(endLayout);
		endPanel.setBackground(Color.WHITE);
		JLabel endLabel = new JLabel("End Date");
		endPanel.add(endLabel);
		JDatePicker endDatePicker = new JDatePicker(true);
		endListener = new DateListener();
		endDatePicker.addDateSelectionListener(endListener);
		endListener.selectedDate = new Date();
		endPanel.add(endDatePicker);
		menuGrid.setConstraints(endPanel, menuc);
		menuPanel.add(endPanel);
		
		menuc.insets = new Insets(15, 5, 5, 5);
		genButton = new Button("Generate Data");
		genButton.addActionListener(this);
		menuGrid.setConstraints(genButton, menuc);
		menuPanel.add(genButton);

		*/
		
		mainPanel.revalidate();
		
		setContentPane(mainPanel);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==genButton){
			String today = new Date().toString().substring(4,10) + ", " + new Date().toString().substring(24);
			DateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date firstDate = null;
			try {
				firstDate = parseDate.parse(times[0].substring(0, 11) + "00:00:00");
			} catch (Exception exception) {}
			String firstDateString = firstDate.toString().substring(4,10) + ", " + firstDate.toString().substring(24);
			
			//startListener.selectedDate.toString().substring(8,10)
			if(startListener.selectedDate==null){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "Invalid start date");
			}
			else if(endListener.selectedDate==null){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "Invalid end date");
			}
			else if(startListener.selectedDate.compareTo(new Date())>0 || endListener.selectedDate.compareTo(new Date())>0){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "No data available past " + today);
			}
			else if(firstDate.compareTo(startListener.selectedDate)>0 || firstDate.compareTo(endListener.selectedDate)>0){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "No data available prior to " + firstDateString);
			}
			else if(startListener.selectedDate.compareTo(endListener.selectedDate)>0){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "End date is prior to start date");
			}
			else{
				int startIndex = 0;
				int endIndex = 0;
				Date startDate = null;
				Date endDate = null;
				try {
					startDate = parseDate.parse(parseDate.format(startListener.selectedDate).substring(0,11) + "00:00:00");
					endDate = parseDate.parse(parseDate.format(endListener.selectedDate).substring(0,11) + "23:59:59");
				} catch (Exception exception) {}
				
				boolean setStart = false;
				for(int i = 0; i < times.length; i++){
					Date d = null;
					try {
						d = parseDate.parse(times[i]);
					} catch (Exception exception) {}
					if(d.compareTo(startDate) > 0 && !setStart){
						startIndex = i;
						setStart = true;
					}
					if(d.compareTo(endDate) > 0){
						endIndex = i;
						break;
					}
					if(i==times.length-1)
						endIndex = i+1;
				}
				
				ArrayList<Integer> displayMap = new ArrayList<Integer>();
				switch(interval.getSelectedIndex()){
					case 0:
						for(int i = 0; i < endIndex-startIndex; i++)
							if(i%2==0)
								displayMap.add(i+startIndex);
							else if(i!=0 && i < endIndex-startIndex-1 && Integer.parseInt(times[i+startIndex+1].substring(11,13))-Integer.parseInt(times[i+startIndex-1].substring(11,13))>1 )
								displayMap.add(i+startIndex);
						break;
					case 1:
						for(int i = 0; i < endIndex-startIndex; i++)
							if(i%6 == 0 && i%2==0)
								displayMap.add(i+startIndex);
						break;
					case 2:
						for(int i = 0; i < endIndex-startIndex; i++)
							if(i%12 == 0 && i%2==0)
								displayMap.add(i+startIndex);
						break;
					case 3:
						for(int i = 0; i < endIndex-startIndex; i++)
							if(i%48 == 0 && i%2==0)
								displayMap.add(i+startIndex);
						break;
				}
				
				String displayTimes[] = new String[displayMap.size()];
				String displayTemps[] = new String[displayMap.size()];
				
				for(int i = 0; i < displayTimes.length; i++){
					displayTimes[i] = times[displayMap.get(i)];
					displayTemps[i] = temps[displayMap.get(i)];
				}
				
				chartTimes = new String[(int) Math.ceil((double)(displayTimes.length)/24)][];
				chartTemps = new String[(int) Math.ceil((double)(displayTemps.length)/24)][];
				
				for(int i = 0; i < displayTimes.length; i++){
					if(i%24==0){
						int size = Math.min(24, displayTimes.length-i);
						chartTimes[i/24] = new String[size];
						chartTemps[i/24] = new String[size];
					}
					chartTimes[i/24][i%24] = displayTimes[i];
					chartTemps[i/24][i%24] = displayTemps[i];
				}
				
				DefaultCategoryDataset test = new DefaultCategoryDataset();
				for(int i = 0; i < chartTimes[0].length; i++)
					test.setValue(Double.parseDouble(chartTemps[0][i])-273.15, "temp", chartTimes[0][i].substring(5,16));
				JFreeChart chart = ChartFactory.createLineChart("", "Time (Military)", "Temperature (°C)", test, PlotOrientation.VERTICAL, false, true, false);
				CategoryPlot plot = (CategoryPlot)chart.getPlot();
		        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
				xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		        //xAxis.setTickLabelFont(getFont().deriveFont(10.0f));
				//ValueAxis range = chart.getCategoryPlot().getRangeAxis();
				//range.setRange(270,280);
				ChartPanel chartPanel = new ChartPanel(chart);
				chartPanel.setPreferredSize(new Dimension(740, 300));
				chartContainer.removeAll();
				chartContainer.add(chartPanel);
				
				Object[][] data = new Object[displayTimes.length][2];
				
				for(int i = 0; i < displayTimes.length; i++){
					data[i][0] = displayTimes[i].substring(5,16);
					data[i][1] = Math.floor((Double.parseDouble(displayTemps[i])-273.15)*100)/100;
				}
				
				String[] columnNames = {"Time (Military)","Temperature (°C)"};
				
				JTable table = new JTable(data, columnNames){
					private static final long serialVersionUID = 1L;

					@Override
					public boolean isCellEditable(int row, int col){
						return false;
					}
				};
				
				
				table.getColumnModel().getColumn(0).setPreferredWidth(94);
				table.getColumnModel().getColumn(1).setPreferredWidth(98);
				
				tableContainer = new JScrollPane(table);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tableContainer.setPreferredSize(new Dimension(210,340));
				tablePanel.removeAll();
				tablePanel.add(tableContainer);
				
				page = 0;
				if(chartTimes.length==1)
					nextButton.setEnabled(false);
				else
					nextButton.setEnabled(true);
				
				mainPanel.revalidate();
			}
		}
		if(e.getSource()==backButton){
			page--;
			if(page==0)
				backButton.setEnabled(false);
			if(page==chartTimes.length-2)
				nextButton.setEnabled(true);
			DefaultCategoryDataset test = new DefaultCategoryDataset();
			for(int i = 0; i < chartTimes[page].length; i++)
				test.setValue(Double.parseDouble(chartTemps[page][i])-273.15, "temp", chartTimes[page][i].substring(5,16));
			JFreeChart chart = ChartFactory.createLineChart("", "Time", "Temperature (°C)", test, PlotOrientation.VERTICAL, false, true, false);
			CategoryPlot plot = (CategoryPlot)chart.getPlot();
	        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(740, 300));
			chartContainer.removeAll();
			chartContainer.add(chartPanel);
			mainPanel.revalidate();
		}
		if(e.getSource()==nextButton){
			page++;
			if(page == chartTimes.length-1)
				nextButton.setEnabled(false);
			if(page==1)
				backButton.setEnabled(true);
			DefaultCategoryDataset test = new DefaultCategoryDataset();
			for(int i = 0; i < chartTimes[page].length; i++)
				test.setValue(Double.parseDouble(chartTemps[page][i])-273.15, "temp", chartTimes[page][i].substring(5,16));
			JFreeChart chart = ChartFactory.createLineChart("", "Time", "Temperature (°C)", test, PlotOrientation.VERTICAL, false, true, false);
			CategoryPlot plot = (CategoryPlot)chart.getPlot();
	        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(740, 300));
			chartContainer.removeAll();
			chartContainer.add(chartPanel);
			mainPanel.revalidate();
		}
	}
}

class DateListener implements DateSelectionListener {
	public Date selectedDate;
	  public void dateSelectionChanged(DateSelectionEvent evt) {
	    selectedDate = evt.getLastDate();
	  }
	  public void disabledDatesChanged(DateSelectionEvent evt) {}
	  public void selectionModeChanged(DateSelectionEvent evt) {}
	  public void emptySelectionAllowedChanged(DateSelectionEvent evt) {}
	@Override
	public void disabledDateSelectionAttempted(DateSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}