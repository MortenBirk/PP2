package com.pp2.starlords.pp2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.*;
import java.util.*;
import android.content.Context;

public class FileLogger{

    public static void initFile(Context context, String fileName) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write("".getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void write(String s, String fileName, Context context) {

        FileOutputStream outputStream;

        s += "\n";

        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * File is a new line seperated list of single double values
     */
    public static List<Position> parseFile(String fileName, Context context) {
        List<Position> res = new ArrayList<Position>();

        try {

            FileInputStream fin = context.openFileInput(fileName);

            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            //string temp contains all the data of the file.
            fin.close();

            List<String> posStrings = Arrays.asList(temp.split("\n"));
            System.out.println(temp);

            //String line = null;

//            double total = 0;
//            int num = 0;
//            while((line = br.readLine()) != null) {
//                String pos[] = line.split(",");
//                Double lat = Double.parseDouble(pos[0]);
//                Double lng = Double.parseDouble(pos[1]);
//
//                res.add(new Position(lat,lng)); // TODO : bug in position parsing
//            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return res;
    }


}