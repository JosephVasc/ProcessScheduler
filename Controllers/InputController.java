package Controllers;

import java.util.Scanner;
import Models.Process;
import java.util.ArrayList;
import java.io.File;
import java.io.*;



public class InputController {
    private ArrayList<Process> allProcesses;
    
    private int processPriority;
    private int burstTime;
    private int arrivingTime;
    private String processName;    
    private int number;
    private Scanner keyboard;
  

    public InputController() {
        allProcesses = new ArrayList<Process>();
        number = 0;
        processPriority = 0;
        arrivingTime = 0;
        burstTime = 0;
        processName = null;
    }

    public ArrayList<Process> getAllProcesses() throws IOException {

            System.out.println("Starting.. Reading jobs from file, Outputting to a file ");
            System.out.println("FILE PATH: ");
            System.out.println(System.getProperty("user.dir"));
            openFile();
            do{
            readFromFile();
            }while(keyboard.hasNext());
            closeFile();            
            return allProcesses;
    }
    public void readFromFile() throws IOException
    {
      while(keyboard.hasNext())
      {
         String input = keyboard.nextLine();
         processScanner(input);
         
         allProcesses.add(new Process(++number, processPriority, arrivingTime, burstTime, processName));

      
      
      }
    }
    public void openFile()
    {
      try{
         keyboard = new Scanner(new File("input.txt"));
    }catch(Exception e){
      System.out.println("couldnt find file");
    }
    }
    public void closeFile()
    {
      keyboard.close();
    }
    public void processScanner(String inc) throws IOException
    {

        String[] incArr = inc.split(" ");
        
        System.out.println(inc);

        for(int i = 0; i < incArr.length; i++ )
        {
            System.out.println(incArr[i]);
            if(incArr[1].equals(" "))
            {
                incArr[1] = incArr[2];
            }
        }

        String time, cmd, prio, jobNum, Est = "";

        time = incArr[0];
        cmd = incArr[1];

        switch(cmd)
        {
            case "J":
                prio = incArr[2];
                Est = incArr[3];
                processPriority = Integer.parseInt(prio);
                burstTime = Integer.parseInt(Est);
                break;
            case "W":
                break;
            case "R":
                jobNum = incArr[2];
                number = Integer.parseInt(jobNum);
                break;
            case "C":
                break;
            case "T":
                jobNum = incArr[2];
                number = Integer.parseInt(jobNum);
                break;
            default:
                System.out.println("error processing");
        }

        arrivingTime = Integer.parseInt(time);
        processName = cmd;

        PrintStream consoleOutput = new PrintStream(new File("output.txt"));

        PrintStream currentPrints = System.out;

        System.setOut(consoleOutput);



    }
        

}
