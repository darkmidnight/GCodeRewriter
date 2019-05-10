
package com.darkmidnight.gcoderewriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author anthony
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Need to provide 2 file paths, one for input and one for output.");
        }
        try {
            boolean isDown = false;
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
            FileWriter fw = new FileWriter(new File(args[1]));
            while ((line = br.readLine()) != null) {
                if (line.contains("Z-") && !isDown) {
                    fw.write("M3 S900\n");
                    fw.write("G4 P1\n");
                    isDown = true;
                } else if (line.contains("Z") && !line.contains("Z-") && isDown) {
                    fw.write("M3 S100\n");
                    fw.write("G4 P1\n");
                    isDown = false;
                }
                fw.write(line + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
