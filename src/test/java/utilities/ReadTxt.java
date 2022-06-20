package utilities;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadTxt {


    public static String getId(){
        String id="";
        try{
            //identify file location
            FileReader fileReader = new FileReader(ConfigurationReader.getProperty("newUser_filename"));
            //Read the records of the file in given location
            BufferedReader br = new BufferedReader(fileReader);
            String line = br.readLine();//856-45-6789,
            while(line != null){
                id = line.split(",")[2];
               // id=Integer.parseInt(Stringid);
                line = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return id.substring(4);
    }



}

