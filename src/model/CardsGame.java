package model;

import javax.swing.ImageIcon;

public interface CardsGame extends Model{
 
	public void nRandomIntegers(int n);
//	public JLabel[] getPhotoLabel();
	public ImageIcon[] getImagePhotoArr();
	public ImageIcon getImagecover();
	public int scoreCalc(int whosTurn);
	
}


