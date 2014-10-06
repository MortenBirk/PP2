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
            for (String s : posStrings) {
                String[] loc = s.split(",");
                Position position = new Position(Double.parseDouble(loc[0]), Double.parseDouble(loc[1]));
                res.add(position);
            }
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

    public static List<Position> getGroundTruth() {
        ArrayList<Position> valueToReturn = new ArrayList<Position>();
        valueToReturn.add(new Position(56.17219114547839, 10.188129544258118, true));
        valueToReturn.add(new Position(56.17189250776975, 10.188006162643433, true));
        valueToReturn.add(new Position(56.171593867738345, 10.187898874282837, true));
        valueToReturn.add(new Position(56.17139676404523, 10.187796950340271, true));
        valueToReturn.add(new Position(56.17108916080561, 10.187652111053467, true));
        valueToReturn.add(new Position(56.17071286611291, 10.187469720840454, true));
        valueToReturn.add(new Position(56.17067702833077, 10.187743306159973, true));
        valueToReturn.add(new Position(56.17071585259325, 10.187855958938599, true));
        valueToReturn.add(new Position(56.17097866195288, 10.187968611717224, true));
        valueToReturn.add(new Position(56.17108916080561, 10.188016891479492, true));
        valueToReturn.add(new Position(56.17123549663467, 10.18807590007782, true));
        valueToReturn.add(new Position(56.17143857400407, 10.188161730766296, true));
        valueToReturn.add(new Position(56.17153413945333, 10.188140273094177, true));
        valueToReturn.add(new Position(56.17161178620574, 10.188247561454773, true));
        valueToReturn.add(new Position(56.171755133643785, 10.188606977462769, true));
        valueToReturn.add(new Position(56.17188354860259, 10.188692808151245, true));
        valueToReturn.add(new Position(56.17200599037265, 10.188757181167603, true));
        valueToReturn.add(new Position(56.1721224590111, 10.18882691860199, true));
        valueToReturn.add(new Position(56.172203090938446, 10.188236832618713, true));
        return valueToReturn;
    }

}