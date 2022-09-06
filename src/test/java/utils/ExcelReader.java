package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader  {


    static Workbook book;
    static Sheet sheet;

    public static void openExcel (String path){
        try{
            FileInputStream fis = new FileInputStream(path);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // zwraca Sheet
    public static void getSheet(String sheetName){
        sheet = book.getSheet(sheetName);
    }
    // zwraca liczbe rzedow w sheet
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    // zwraca liczbe kolumn w sheet
    public static int getColumntCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }
     //zwraca dane z komorek w postaci Strings
    public static String getCellData (int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }




}
