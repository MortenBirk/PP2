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

    public static String getGroundTruth() {
        return "56.17195223549727, 10.189502835273743\n" +
                "56.17199404485128, 10.18929898738861\n" +
                "56.17201793589028, 10.189121961593628\n" +
                "56.172041826914416, 10.188896656036377\n" +
                "56.17205675879694, 10.188741087913513\n" +
                "56.1719313308032, 10.188676714897156\n" +
                "56.17179992960843, 10.188617706298828\n" +
                "56.17171929683442, 10.18859088420868\n" +
                "56.17168644639649, 10.18855333328247\n" +
                "56.17163567748257, 10.188762545585632\n" +
                "56.17159685415014, 10.188982486724854\n" +
                "56.17156699002159, 10.189213156700134\n" +
                "56.171537125869776, 10.189422369003296\n" +
                "56.17150128885698, 10.189620852470398\n" +
                "56.171468438232516, 10.189851522445679\n" +
                "56.17142961473101, 10.19008755683899\n" +
                "56.17139079119024, 10.1903235912323\n";
    }

}