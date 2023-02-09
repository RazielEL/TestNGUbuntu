package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static Properties readProperties(String filePath){


        try {
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException e){
            e.printStackTrace();;
        } catch (IOException e){
            e.printStackTrace();
        }
        return prop;

    }// uniwersalna metoda ktora bedzie czytac kazdy plik z properties

    public static String getPropertyValue (String key){ //a ta metoda bierze pojedyncza wartosc na podstawie klucza
        return prop.getProperty(key);
    }




}


