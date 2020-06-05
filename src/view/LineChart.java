package view;
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.data.category.DefaultCategoryDataset;  
	public class LineChart extends JFrame {  
		  
		  private static final long serialVersionUID = 1L;  
		  
		  public LineChart(DefaultCategoryDataset data) {  
		    super("גרף התקדמות עבור ילד");  
		    // Create dataset  
		    DefaultCategoryDataset dataset = data;  
		    // Create chart  
		    JFreeChart chart = ChartFactory.createLineChart(  
		        "גרף התקדמות עבור ילד", // Chart title  
		        "משחק", // X-Axis Label  
		        "ניקוד", // Y-Axis Label  
		        dataset  
		        );  
		  
		    ChartPanel panel = new ChartPanel(chart);  
		    setContentPane(panel);  
		  } 
	   
}
