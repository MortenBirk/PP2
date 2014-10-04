package com.pp2.starlords.pp2;



import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
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

}