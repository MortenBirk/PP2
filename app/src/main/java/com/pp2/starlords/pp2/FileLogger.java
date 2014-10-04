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


  public FileLogger(String fileName, Context context) {
    try {
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
  public List<Position> parseFile(String fileName) {
    List<Position> res = new ArrayList<Position>();

    try {
      File file = new File(args[0]);
      FileReader fileReader = new FileReader(file);

      BufferedReader br = new BufferedReader(fileReader);
      String line = null;

      float total = 0;
      int num = 0;
      while((line = br.readLine()) != null) {
        Double d = Double.parseDouble(line);

        res.add(d);
      }
    } catch(IOException e) {
      e.printStackTrace();
    }

    return res;
  } 


}