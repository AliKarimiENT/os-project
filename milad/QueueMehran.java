package com.ma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class QueueMehran {
  private final List<Process> processes = new ArrayList<>();
  
  public void generateProcesses() throws FileNotFoundException {
    FileOutputStream fos = new FileOutputStream("processes.txt");
    StringBuilder fileData = new StringBuilder();
    for(int i = 1; i <= 100000; i++) 
      fileData
        .append("Process: ")
        .append(i)
        .append("-")
        .append(new Random().nextInt(10000))
        .append("-")
        .append(new Random().nextInt(10000))
        .append("-")
        .append(new Random().nextInt(10000))
        .append("\n");

    try {
      fos.write(fileData.toString().getBytes());
      fos.flush();
      fos.close();
    } catch (IOException e) {
      System.out.println("Queue file couldn't be written.\n");
      e.printStackTrace();
    }
  }
  
  public List<Process> getReadyQueue() {
    BufferedReader br = null;
    try {
      File file = new File("processes.txt");
      if (processes.size() > 0) processes.clear();
      FileReader fr = new FileReader(file);
      br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        String[] processSplitted = line.replace("Process: ", "").split("-");
        processes.add(
          new Process(
            Integer.parseInt(processSplitted[0]),
            Integer.parseInt(processSplitted[1]), 
            Integer.parseInt(processSplitted[2]), 
            Integer.parseInt(processSplitted[3])
          )
        );
      }
    }
    
    catch(IOException e) { e.printStackTrace(); }
    finally {
      try { if (br != null) br.close(); }
      catch(IOException e) { e.printStackTrace(); }
    }

    return processes.subList(0, 100);
  }

  public List<Process> completeReadyQueue(List<Process> readyQueue) {
    int countToFill = 100 - readyQueue.size();
    if (processes.size() > 0)
      for(int i = 1; i <= countToFill; i++) readyQueue.add(processes.get(i));
    else {
      BufferedReader br = null;
      try {
        File file = new File("processes.txt");
        FileReader fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line;
        int counter = 0;
        while ((line = br.readLine()) != null) {
          counter += 1;
          String[] processSplitted = line.replace("Process: ", "").split("-");
          if (counter <= countToFill)
            readyQueue.add(
              new Process(
                Integer.parseInt(processSplitted[0]),
                Integer.parseInt(processSplitted[1]),
                Integer.parseInt(processSplitted[2]),
                Integer.parseInt(processSplitted[3])
              )
            );
        }
      }

      catch(IOException e) { e.printStackTrace(); }
      finally {
        try { if (br != null) br.close(); }
        catch(IOException e) { e.printStackTrace(); }
      }
    }

    return readyQueue;
  }


  }
