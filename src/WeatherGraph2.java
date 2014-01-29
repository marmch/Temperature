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

//import com.mysql.jdbc.*;

public class WeatherGraph extends JApplet implements ActionListener{
	private static final long serialVersionUID = 1L;

	JPanel mainPanel;
	JPanel chartContainer;
	String temps[];
	String times[];
	
	Choice city;
	Choice interval;
	
	JTextField startDate;
	JTextField endDate;
	
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
		
		String param = getParameter("temps");
		temps = param.split(";");
		param = getParameter("times");
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
		
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		JPanel startPanel = new JPanel();
		GridLayout startLayout = new GridLayout(0,1);
		startPanel.setLayout(startLayout);
		startPanel.setBackground(Color.WHITE);
		JLabel startLabel = new JLabel("Start Date");
		startPanel.add(startLabel);
		startDate = new JTextField(date.format(new Date()));
		startPanel.add(startDate);
		menuGrid.setConstraints(startPanel, menuc);
		menuPanel.add(startPanel);
		
		JPanel endPanel = new JPanel();
		GridLayout endLayout = new GridLayout(0,1);
		endPanel.setLayout(endLayout);
		endPanel.setBackground(Color.WHITE);
		JLabel endLabel = new JLabel("End Date");
		endPanel.add(endLabel);
		endDate = new JTextField(date.format(new Date()));
		endPanel.add(endDate);
		menuGrid.setConstraints(endPanel, menuc);
		menuPanel.add(endPanel);
		
		menuc.insets = new Insets(15, 5, 5, 5);
		genButton = new Button("Generate Data");
		genButton.addActionListener(this);
		menuGrid.setConstraints(genButton, menuc);
		menuPanel.add(genButton);

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
			
			Date start = null;
			Date end = null;
			
			DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			try{
				start = date.parse(startDate.getText());
				end = date.parse(endDate.getText());
				if(startDate.getText().length()!=10 || endDate.getText().length()!=10){
					JFrame frame = new JFrame("Invalid Input");
					JOptionPane.showMessageDialog(frame, "Enter date in the following format: YYYY-MM-DD");
					return;
				}
			}
			catch(Exception exception){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "Enter date in the following format: YYYY-MM-DD");
				return;
			}
			if(start.compareTo(new Date())>0 || end.compareTo(new Date())>0){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "No data available past " + today);
			}
			else if(firstDate.compareTo(start)>0 || firstDate.compareTo(end)>0){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "No data available prior to " + firstDateString);
			}
			else if(start.compareTo(end)>0){
				JFrame frame = new JFrame("Invalid Input");
				JOptionPane.showMessageDialog(frame, "End date is prior to start date");
			}
			else{
				int startIndex = 0;
				int endIndex = 0;
				Date startDate = null;
				Date endDate = null;
				try {
					startDate = parseDate.parse(date.format(start) + " 00:00:00");
					endDate = parseDate.parse(date.format(end) + " 23:59:59");
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