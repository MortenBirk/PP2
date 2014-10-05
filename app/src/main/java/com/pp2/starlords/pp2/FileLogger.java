package com.pp2.starlords.pp2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.*;
import java.util.*;
import android.content.Context;

public class FileLogger{

    private File file;
    private FileWriter fileWriter;
    private Context context;


    public FileLogger(String fileName, Context context) {
        try {
            this.context = context;
            file = new File(context.getFilesDir(), fileName);
            fileWriter = new FileWriter(file);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String s) {
        try {
            fileWriter.write(s + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * File is a new line seperated list of single double values
     */
    public List<Position> parseFile() {
        List<Position> res = new ArrayList<Position>();

        try {
            FileReader fileReader = new FileReader(file);

            BufferedReader br = new BufferedReader(fileReader);
            String line = null;

            double total = 0;
            int num = 0;
            while((line = br.readLine()) != null) {
                String pos[] = line.split(",");
                Double lat = Double.parseDouble(pos[0]);
                Double lng = Double.parseDouble(pos[1]);

                res.add(new Position(lat,lng)); // TODO : bug in position parsing
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return res;
    }


}