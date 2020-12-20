package Controllers;

import java.util.ArrayList;
import Models.Process;
import Models.CPU;
import java.io.File;
import java.io.*;

public class PreemptiveSchedulingController {

	public static void main(String[] args) throws IOException{
      
      
      PreemptiveScheduler pst = new  PreemptiveScheduler();
		PreemptiveScheduler preemptiveSchedulingLogic = new PreemptiveScheduler();
		ArrayList<Process> allProcesses = new ArrayList<Process>();
		ArrayList<CPU> scheduledProcesses = new ArrayList<CPU>();
		ArrayList<CPU> completionTime = new ArrayList<CPU>();
		ArrayList<CPU> turnAroundTimes = new ArrayList<CPU>();
		ArrayList<CPU> waitingTimes = new ArrayList<CPU>();
      ArrayList<String> jobList = new  ArrayList<String>();
      InputController inputController = new InputController();
      int ListCounter=0;
      
		allProcesses = inputController.getAllProcesses();
		System.out.println("Name Pr  AT  BT       JOB#");
		for (Process process : allProcesses) {
			System.out.println(
			process.getCommandName() + "   " + process.getProcessPriority() + "   " + process.getArrivingTime() + "   " + process.getBurstTime() + "      " + process.getProcessNumber());
		}
     for(Process processes: allProcesses)
	    {
	    	   System.out.println("Processing job: " + processes.getCommandName() + " Arrival Time: " + processes.getArrivingTime());	 
	    	   System.out.println(" INFORMATION: ");
	    	   System.out.println(" Job Number: " + processes.getProcessNumber() +  " Priority: "+ processes.getProcessPriority() +  " estimated time: " + processes.getBurstTime() + "\n" );
	    	   jobList.add(processes.getCommandName());
	    }
      

		ArrayList<Process> copyAllProcesses = new ArrayList<Process>(allProcesses);
		scheduledProcesses = preemptiveSchedulingLogic.calculateProcessSchedule(allProcesses);

		System.out.println("Process execution order : ");
		for (CPU sp : scheduledProcesses) {
			System.out.print(sp.getProcessNumber() + "  ");
		}
		
      
      System.out.println("\nProcess execution time: ");	
		for (CPU sp : scheduledProcesses) {
			System.out.print("Processing: Process Number" + sp.getProcessNumber() + ": Execution Time:  " + sp.getExeTime() + "  ");
		}
      
  

		// get the process completion time
		completionTime = preemptiveSchedulingLogic.getCompletionTimes(copyAllProcesses, scheduledProcesses);

		System.out.println("\nCompletion time: ");
		for (CPU times : completionTime) {
			System.out.println("P" + times.getProcessNumber() + "  " + times.getExeTime() + "  ");
		}
      

		// get Turn Around Time
		turnAroundTimes = preemptiveSchedulingLogic.getTurnAroundTimes(copyAllProcesses, completionTime);
		
		System.out.println("Turn Around Times: ");
		for (CPU times : turnAroundTimes) {
			System.out.println("Process Number: " + times.getProcessNumber() + "  " + times.getExeTime() + "  ");
		}

		// get Waiting Times
	   waitingTimes = pst.getWaitingTimes(copyAllProcesses, turnAroundTimes);

	   System.out.println("Waiting Times: ");
	   for (CPU waitingTime : waitingTimes) {
	      System.out.println("Job :" + jobList.get(ListCounter) + " which was job number: "+ waitingTime.getProcessNumber() + " waited  " + waitingTime.getExeTime() + " \n");
	      if(ListCounter>=jobList.size()-1)
	      {
	        ListCounter=0;
	        break;
	      }
	        else
	        ListCounter++;
	  }
     // final Stats
     System.out.println("final statistics: ");
     
      
            
	}
}