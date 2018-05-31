package in.sandiplayek.d_eye;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Android on 22-11-2017.
 */

public class WriteToFile {
    public static void writeLogToFile(String TAGGING, String value) {
        try{
            File file = new File(Environment.getExternalStorageDirectory(), TAGGING+".txt");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(value);
            writer.newLine();
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createLogCat(){
        try{
            Process process = Runtime.getRuntime().exec("logcat -d");
            process = Runtime.getRuntime().exec( "logcat -f " + "/storage/emulated/0/"+"LogCat.txt");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void createErrorLogCat(){
        try{
            Process process = Runtime.getRuntime().exec("logcat -e");
            process = Runtime.getRuntime().exec( "logcat -e " + "/storage/emulated/0/"+"ErrorLogCat.txt");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
