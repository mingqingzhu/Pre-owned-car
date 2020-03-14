package utils;

import java.io.File;
import java.io.FileOutputStream;

public class SaveFile {

    public void writeFile(String msg) {
        try {
            File filePathLogging = new File( "./SaveCarList.txt");
            FileOutputStream out = new FileOutputStream(filePathLogging, true);
            StringBuffer sb = new StringBuffer();
            sb.append("\t\t\t" + msg + "\n");
            out.write(sb.toString().getBytes("utf-8"));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
