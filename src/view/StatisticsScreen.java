package view;

import java.awt.EventQueue;
import javax.swing.*;    
import java.awt.event.*;
import java.util.Observable;
import java.util.Vector;

import com.toedter.calendar.JDateChooser;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatisticsScreen extends Observable implements View{

	private JFrame frame;
	private JTextField nametext;
	JRadioButton allkids,onekid;
	JLabel startdateall,enddateall,startdateone,enddateone,namekid;
	JDateChooser chooserdatestartall,chooserdateendall,chooserdatestartone,chooserdateendone;
	ButtonGroup bp;
	private JComboBox<String> combobox;
	JButton showtable,showgraph;
	StatisticsScreen graphing;
	private LinearStats linear=new LinearStats();
	//private JComboBox<String> p2List;
	//private JComboBox<String> p1List;
	/**
	 * Launch the application.
	 */
	public class UpdateList{};
	public class LinearStats{
		
		int index;

		public int getIndex() {
			return index;
		}
		public void setIndex(int index)
		{
			this.index=index;
		}
	};
	public class TableStats{};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsScreen window = new StatisticsScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StatisticsScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 517, 339);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Static Page");
		
		JLabel lblNewLabel = new JLabel("Static_Page");
		lblNewLabel.setBounds(193, 0, 82, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Type_of_Static:");
		lblNewLabel_1.setBounds(27, 36, 94, 24);
		frame.getContentPane().add(lblNewLabel_1);
	
		allkids = new JRadioButton("All kids");
		allkids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showgraph.setEnabled(false);
				showtable.setEnabled(true);
				bp=new ButtonGroup();
				bp.add(allkids);bp.add(onekid);
				if(allkids.isSelected())
				{
					chooserdatestartall.setEnabled(true);
					chooserdateendall.setEnabled(true);
					startdateall.setEnabled(true);
					enddateall.setEnabled(true);
		    	}
				else
				{
					chooserdatestartall.setEnabled(false);
					chooserdateendall.setEnabled(false);
					startdateall.setEnabled(false);
					enddateall.setEnabled(false);
				}
				if(onekid.isSelected())
				{
					chooserdatestartone.setEnabled(true);
					chooserdateendone.setEnabled(true);
					namekid.setEnabled(true);
					startdateone.setEnabled(true);
					enddateone.setEnabled(true);
					//nametext.setEnabled(true);
					combobox.setEnabled(true);
				}
				else
				{
					chooserdatestartone.setEnabled(false);
					chooserdateendone.setEnabled(false);
					namekid.setEnabled(false);
					startdateone.setEnabled(false);
					enddateone.setEnabled(false);
					//nametext.setEnabled(false);
					combobox.setEnabled(false);
				}
			}});
		allkids.setBounds(27, 67, 109, 23);
		frame.getContentPane().add(allkids);
		
		onekid = new JRadioButton("One kid");
		onekid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showgraph.setEnabled(true);
				showtable.setEnabled(false);
				bp=new ButtonGroup();
				bp.add(allkids);bp.add(onekid);
				if(onekid.isSelected())
				{
					chooserdatestartone.setEnabled(true);
					chooserdateendone.setEnabled(true);
					namekid.setEnabled(true);
					startdateone.setEnabled(true);
					enddateone.setEnabled(true);
					//nametext.setEnabled(true);
					combobox.setEnabled(true);
				}
				else
				{
					chooserdatestartone.setEnabled(false);
					chooserdateendone.setEnabled(false);
					namekid.setEnabled(false);
					startdateone.setEnabled(false);
					enddateone.setEnabled(false);
					//nametext.setEnabled(false);
					combobox.setEnabled(false);
				}
				if(allkids.isSelected())
				{
					chooserdatestartall.setEnabled(true);
					chooserdateendall.setEnabled(true);
					startdateall.setEnabled(true);
					enddateall.setEnabled(true);
		    	}
				else
				{
					chooserdatestartall.setEnabled(false);
					chooserdateendall.setEnabled(false);
					startdateall.setEnabled(false);
					enddateall.setEnabled(false);
				}
				setChanged();
				notifyObservers(new UpdateList());
			}
		});
		onekid.setBounds(27, 104, 109, 23);
		frame.getContentPane().add(onekid);
		
		showgraph = new JButton("Show graph");
		showgraph.setBounds(205, 181, 119, 23);
		frame.getContentPane().add(showgraph);
		
		showgraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setChanged();
				notifyObservers(linear);
			}
			});
		
		
		showtable = new JButton("table");
		showtable.setBounds(205, 215, 119, 23);
		frame.getContentPane().add(showtable);
		
		showtable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setChanged();
				notifyObservers(new TableStats());
			}
			});
		
		startdateall = new JLabel("Start_Date:");
		startdateall.setEnabled(false);
		startdateall.setBounds(136, 71, 72, 14);
		frame.getContentPane().add(startdateall);
		
		enddateall = new JLabel("End_Date:");
		enddateall.setEnabled(false);
		enddateall.setBounds(136, 108, 64, 14);
		frame.getContentPane().add(enddateall);
		
		chooserdatestartall = new JDateChooser();
		chooserdatestartall.setEnabled(false);
		chooserdatestartall.setBounds(214, 67, 78, 19);
		frame.getContentPane().add(chooserdatestartall);
		
		chooserdateendall = new JDateChooser();
		chooserdateendall.setEnabled(false);
		chooserdateendall.setBounds(214, 104, 78, 19);
		frame.getContentPane().add(chooserdateendall);
		
		namekid = new JLabel("Name:");
		namekid.setEnabled(false);
		namekid.setBounds(333, 63, 46, 14);
		frame.getContentPane().add(namekid);
		
		//nametext = new JTextField();
		//nametext.setEnabled(false);
		//nametext.setBounds(365, 60, 126, 20);
		//frame.getContentPane().add(nametext);
		//nametext.setColumns(10);
		
		combobox= new JComboBox<>(new Vector());
		combobox.setEnabled(false);
		combobox.setBounds(365,60,126,20);
		frame.getContentPane().add(combobox);
		
	
		combobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				combo(e);
			}
			});
		
		
		startdateone = new JLabel("Start_Date:");
		startdateone.setEnabled(false);
		startdateone.setBounds(333, 104, 82, 14);
		frame.getContentPane().add(startdateone);
		
		chooserdatestartone = new JDateChooser();
		chooserdatestartone.setEnabled(false);
		chooserdatestartone.setBounds(413, 104, 78, 19);
		frame.getContentPane().add(chooserdatestartone);
		
		enddateone = new JLabel("End_Date:");
		enddateone.setEnabled(false);
		enddateone.setBounds(333, 134, 64, 14);
		frame.getContentPane().add(enddateone);
		
		chooserdateendone = new JDateChooser();
		chooserdateendone.setEnabled(false);
		chooserdateendone.setBounds(413, 129, 78, 19);
		frame.getContentPane().add(chooserdateendone);
		frame.setVisible(false);
	}

	public void updateList(Vector arg) {
		
		combobox.setModel(new DefaultComboBoxModel(arg));
		//p1List.setModel(new DefaultComboBoxModel(arg));
		//p2List.setModel(new DefaultComboBoxModel(arg));
	}

	public void Showgraph(DefaultCategoryDataset arg) {
		
		//ApplicationFrame frame=new ApplicationFrame("graph");
	//	ChartPanel chartPanel = new ChartPanel( lineChart );
	  //    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	    //  frame.setContentPane( chartPanel );
		 SwingUtilities.invokeLater(() -> {  
		      LineChart example = new LineChart(arg);
		      example.setAlwaysOnTop(true);  
		      example.pack();  
		      example.setSize(600, 400);  
		      example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
		      example.setVisible(true);  
		    });  
	}

	public void Showtable(JTable jt) {
		 JFrame f;
		 f=new JFrame(); 	
		JScrollPane sp=new JScrollPane(jt);    
		 f.add(sp);          
		 f.setSize(300,400);    
		 f.setVisible(true);
	}
	
	public void combo(ActionEvent e)
	{
		int indexSelected=combobox.getSelectedIndex();
		linear.setIndex(indexSelected);
	}
	
	public void display(){frame.setVisible(true);}
	public void undisplay(){frame.setVisible(false);}
}

