package model;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public interface CardsGame {
	public void nRandomIntegers(int n);
//	public JLabel[] getPhotoLabel();
	public ImageIcon[] getImagePhotoArr();
	public ImageIcon getImagecover();
	public int scoreCalc(int whosTurn);
}


