package model;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;
import java.io.*;
import java.util.*;
import org.apache.poi.*;
import org.apache.poi.ss.usermodel.*;  
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Observable;

public class Data extends Observable implements Model
{
int rowNumCL,rowNumGH;
Sheet childrenList,gameHistory;
private static String[] childSheetColumns = {"שם מלא", "תעודת זהות"};
private static String[] gamesSheetColumns = {"שם מלא", "תעודת זהות","תאריך המשחק","סוג המשחק","ניקוד"};
Workbook dataFile;//this will be the file that will contain the two sheets of data we want to save
CellStyle dateCellStyle;
Vector<String> children = new Vector<String>(); // Create an ArrayList object
FileOutputStream fileOut;
FileInputStream inputStream;
String excelFilePath="DataFile.xlsx";
File f=new File(excelFilePath);
boolean update=false;
DataFormatter formatter = new DataFormatter();
Cell cell2compare;
public Data()
{
	if(f.exists()) //in case that the DataFile already exists we just update it
	{
		update=true;
		try {
			inputStream=new FileInputStream(f);
			dataFile=WorkbookFactory.create(inputStream);
			childrenList=dataFile.getSheetAt(0);
			gameHistory=dataFile.getSheetAt(1);
			rowNumCL=childrenList.getLastRowNum();
			rowNumGH=gameHistory.getLastRowNum();
			rowNumCL++;
			rowNumGH++;
			Row row;
			for(int i=1;i<rowNumCL;i++) //update children vector according to childrenList 
			{
				row=childrenList.getRow(i);
				cell2compare=row.getCell(0);
				String strValue2 = formatter.formatCellValue(cell2compare);
				children.add(strValue2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else//there is no existing DataFile yet-> we will create it 
	{
		dataFile=new XSSFWorkbook();
	    CreationHelper createHelper = dataFile.getCreationHelper();
		childrenList=dataFile.createSheet("רשימת ילדים");//creating new sheet
		gameHistory=dataFile.createSheet("היסטוריית משחקים");//creating new sheet
		 // Create a Font for styling header cells
	    Font headerFont = dataFile.createFont();
	    headerFont.setBold(true);
	    headerFont.setFontHeightInPoints((short) 14);
	    headerFont.setColor(IndexedColors.RED.getIndex());
	
	    // Create a CellStyle with the font
	    CellStyle headerCellStyle = dataFile.createCellStyle();
	    headerCellStyle.setFont(headerFont);
	    
	    // Create a Row for each sheet
	    Row headerRowCL = childrenList.createRow(0);
	    Row headerRowGH = gameHistory.createRow(0);
	
	    // Create cells in both Sheets
	    for(int i = 0; i < childSheetColumns.length; i++) {
	        Cell cell = headerRowCL.createCell(i);
	        cell.setCellValue(childSheetColumns[i]);
	        cell.setCellStyle(headerCellStyle);
	    }
	    for(int i = 0; i < gamesSheetColumns.length; i++) {
	        Cell cell = headerRowGH.createCell(i);
	        cell.setCellValue(gamesSheetColumns[i]);
	        cell.setCellStyle(headerCellStyle);
	    }
	    
	 // Create Cell Style for formatting Date
	    dateCellStyle = dataFile.createCellStyle();
	    dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
	    
		for(int i = 0; i < gamesSheetColumns.length; i++) {
			gameHistory.autoSizeColumn(i);}
			
	    rowNumCL=1;
	    rowNumGH=1;
    }
}
public void addChild(Children child) 
{
	Row row=childrenList.createRow(rowNumCL++);
	row.createCell(0).setCellValue(child.getName());
	row.createCell(1).setCellValue(child.getId());
	for(int i = 0; i < childSheetColumns.length; i++) {
		childrenList.autoSizeColumn(i);
    }
	children.add(child.getName());
	/*Liat TEST -> 
	 setChanged();
	notifyObservers(children);LIAT TEST!!!!*/
}
public void saveGameDetails(GameRecord gameR)
{
	Row row=gameHistory.createRow(rowNumGH++);
	row.createCell(0).setCellValue(gameR.getChildName());
	row.createCell(1).setCellValue(gameR.getChildID());
	Cell dateCell=row.createCell(2);
	dateCell.setCellValue(gameR.getGameDate());
	dateCell.setCellStyle(dateCellStyle);
	row.createCell(3).setCellValue(gameR.getGameType());
	row.createCell(4).setCellValue(gameR.getScore());
	for(int i = 0; i < gamesSheetColumns.length; i++) {
		gameHistory.autoSizeColumn(i);}
}
public void getChildrenList()
{
	setChanged();
	notifyObservers(children);
}

public void deleteChild(int index)
{
	Row row;
	String child=children.get(index).toString();
	row=childrenList.getRow(index+1);//+1 because of the header row
	childrenList.removeRow(row);
	if((index+1)!=(rowNumCL-1))
	{
		childrenList.shiftRows(index+2, rowNumCL-1, -1);
	}
	rowNumCL--;
	children.remove(index);
	for(int i=1;i<rowNumGH;i++)
	{
		row=gameHistory.getRow(i);
		cell2compare=row.getCell(0);
		String strValue2 = formatter.formatCellValue(cell2compare);
		if(Objects.equals(child, strValue2))
		{
			gameHistory.removeRow(row);
			if(i!=(rowNumGH-1))
			{
				gameHistory.shiftRows(i+1, rowNumGH-1, -1);
			}
			rowNumGH--;
		}
	}
}

public void closeFile() // when closing tha app -> close the in/output files 
{
	try
	{
		if(update) inputStream.close();
		fileOut = new FileOutputStream("DataFile.xlsx"); /// else create new file!!!!!
		dataFile.write(fileOut);
		dataFile.close();
	}
	catch(Exception e) {e.printStackTrace();}
	System.exit(0);
	}
}

