package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
public class ChildManagementScreen extends Observable implements View{

	public JFrame frame;
	private JLabel label,selectChildName,fullnameLabel,idLabel;
	private JComboBox<String> addOrRemoveC;
	JComboBox<String> childList;
	private JTextPane fullNameText,idText;
	private JButton completeTheAction;
//	Data list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChildManagementScreen window = new ChildManagementScreen();
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
	public ChildManagementScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ניהול מאגר ילדים");
		frame.setBounds(100, 100, 626, 377);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("\u05E0\u05D9\u05D4\u05D5\u05DC \u05E8\u05E9\u05D9\u05DE\u05EA \u05D4\u05D9\u05DC\u05D3\u05D9\u05DD");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Guttman Kav-Light", Font.PLAIN, 34));
		label.setBounds(163, 13, 270, 26);
		frame.getContentPane().add(label);
		
		addOrRemoveC = new JComboBox<>();
		addOrRemoveC.setToolTipText("\u05E4\u05E2\u05D5\u05DC\u05D4");
		addOrRemoveC.setMaximumRowCount(2);
		addOrRemoveC.setModel(new DefaultComboBoxModel(new String[] {"\u05D4\u05D5\u05E1\u05E4\u05EA \u05D9\u05DC\u05D3", "\u05DE\u05D7\u05D9\u05E7\u05EA \u05D9\u05DC\u05D3"}));
		addOrRemoveC.setBounds(452, 54, 129, 33);
		frame.getContentPane().add(addOrRemoveC);
		addOrRemoveC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAction(e);
			}
		});
		
		fullNameText = new JTextPane();
		fullNameText.setBounds(335, 127, 246, 26);
		frame.getContentPane().add(fullNameText);
		
		idText = new JTextPane();
		idText.setBounds(61, 127, 262, 26);
		frame.getContentPane().add(idText);
		
		idLabel = new JLabel("\u05EA\u05E2\u05D5\u05D3\u05EA \u05D4\u05D6\u05D4\u05D5\u05EA \u05E9\u05DC \u05D4\u05D9\u05DC\u05D3:");
		idLabel.setBounds(172, 98, 151, 16);
		frame.getContentPane().add(idLabel);
		
		fullnameLabel = new JLabel("\u05E9\u05DD \u05D4\u05D9\u05DC\u05D3:");
		fullnameLabel.setBounds(522, 98, 59, 16);
		frame.getContentPane().add(fullnameLabel);
	
		
		selectChildName = new JLabel("\u05D1\u05D7\u05E8 \u05D9\u05DC\u05D3 \u05DC\u05DE\u05D7\u05D9\u05E7\u05D4:");
		selectChildName.setBounds(473, 166, 108, 16);
		frame.getContentPane().add(selectChildName);
		selectChildName.setEnabled(false);
		
		completeTheAction = new JButton("\u05D4\u05E9\u05DC\u05DD \u05D0\u05EA \u05D4\u05E4\u05E2\u05D5\u05DC\u05D4");
		completeTheAction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addOrRemove(e);
			}
		});
		completeTheAction.setBounds(228, 271, 151, 26);
		frame.getContentPane().add(completeTheAction);
		
		childList = new JComboBox<String>();
		childList.setToolTipText("\u05E4\u05E2\u05D5\u05DC\u05D4");
		childList.setMaximumRowCount(2);
		childList.setBounds(452, 196, 129, 33);
		frame.getContentPane().add(childList);
		childList.setEnabled(false);
	}

	protected void addOrRemove(MouseEvent e) {
		/*if(addOrRemoveC.getSelectedItem()=="מחיקת ילד")
		{
			int indexSelected=childList.getSelectedIndex();
			list.deleteChild(indexSelected);
		}
		else
		{
			list.addChild(new Children(fullNameText.getText(),idText.getText()));
		}
		frame.setVisible(false);
	*/
	}
	public void display(){frame.setVisible(true);}
	public void undisplay(){frame.setVisible(false);}
	protected void selectAction(ActionEvent e) {
		if(addOrRemoveC.getSelectedItem()=="מחיקת ילד")
		{
			selectChildName.setEnabled(true);
			childList.setEnabled(true);
			fullnameLabel.setEnabled(false);
			idLabel.setEnabled(false);
			idText.setEnabled(false);
			fullNameText.setEnabled(false);
		}
		else
		{
			selectChildName.setEnabled(false);
			childList.setEnabled(false);
			fullnameLabel.setEnabled(true);
			idLabel.setEnabled(true);
			idText.setEnabled(true);
			fullNameText.setEnabled(true);
		}
	}
}
