package com.pp2.starlords.pp2;


public class FileLogger{

  private File file;
  private FileWriter fileWriter;


  public FileLogger(String fileName) {
    try {
      file = new File(context.getFilesDir(), fileName);
      fileWriter = new FileWriter(file);

    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void write(String s) {
    fileWriter.write(s + "\n");
    fileWriter.flush();
  }

}