package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;


public class WriteToTxt {

    public static void saveRegistrantData(HashMap<String,String>newUser){

        try{
            FileWriter fileWriter = new FileWriter(ConfigurationReader.getProperty("newUser_filename"), true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.append(newUser+"\n");
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}