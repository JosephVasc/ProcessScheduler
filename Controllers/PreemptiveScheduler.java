package Controllers;

import Models.Process;
import Models.CPU;

import java.util.ArrayList;

public class PreemptiveScheduler {

    private ArrayList<CPU> scheduledProcesses;
    int currentTime;
    int totatExecutionTime;
    int executionTime;
    
    Process nextHigherPriority;



    public PreemptiveScheduler() {
        scheduledProcesses = new ArrayList<CPU>();
        currentTime = 0;
        executionTime = 0;
        totatExecutionTime = 0;
        nextHigherPriority = null;
    }

    public ArrayList<CPU> calculateProcessSchedule(ArrayList<Process> allProcesses) {

        
        Process process = this.getFirstArrivingProcess(allProcesses);
     
        Process nextProcess = this.getNextProcess(allProcesses, process.getArrivingTime());

        while (allProcesses.size() > 0 && nextProcess != null) {

            nextHigherPriority = this.getNextHigherPriorityProcess(allProcesses, process);
            executionTime =nextHigherPriority.getArrivingTime() - currentTime;
            totatExecutionTime += executionTime;

            if (currentTime ==nextHigherPriority.getArrivingTime()) {
                process =nextHigherPriority;
            }

            if (executionTime >= process.getBurstTime()) {
                CPU scheduledProceess = new CPU(process.getProcessNumber(),
                        totatExecutionTime);
                scheduledProcesses.add(scheduledProceess);
                allProcesses.remove(process);

                process = this.getNextProcessAfterCurrentProcess(allProcesses, process.getArrivingTime(),
                       nextHigherPriority.getArrivingTime());
            } else {
                process.setBurstTime(process.getBurstTime() - executionTime);
                scheduledProcesses.add(new CPU(process.getProcessNumber(), totatExecutionTime));
                process = this.getNextHigherPriorityProcess(allProcesses, process);

                if (process == null)
                    break;
            }

           if (process.getProcessPriority() == this.getMaximumPriorityProcess(allProcesses).getProcessPriority()) {
              scheduledProcesses = this.processEachMaximum(allProcesses, scheduledProcesses, totatExecutionTime);
            break;
           }
            currentTime += executionTime;
        }
        return scheduledProcesses;
    }

    public Process getNextProcess(ArrayList<Process> allProcesses, int position) {
        if (position + 1 <= allProcesses.size() - 1)
            return allProcesses.get(position + 1);
        return null;
    }

    public Process getMaximumPriorityProcess(ArrayList<Process> allProcesses) {
        int max = Integer.MIN_VALUE;
        Process biggestPriorityProcess = null;
        for (int i = 0; i < allProcesses.size(); i++) {
            if (allProcesses.get(i).getProcessPriority() > max) {
                max = allProcesses.get(i).getProcessPriority();
                biggestPriorityProcess = allProcesses.get(i);
            }
        }
        return biggestPriorityProcess;
    }

    public Process getNextHigherPriorityProcess(ArrayList<Process> allProcesses, Process process) {
        for (int i = 0; i < allProcesses.size(); i++) {
            if (process.getArrivingTime() < allProcesses.get(i).getArrivingTime()
                    && process.getProcessPriority() <= allProcesses.get(i).getProcessPriority()) {
                return allProcesses.get(i);
            }
        }
        return process;
    }

    public Process getFirstArrivingProcess(ArrayList<Process> allProcesses) {
        int min = Integer.MAX_VALUE;
        Process minArrivingTime = null;
        for (int i = 0; i < allProcesses.size(); i++) {
            if (allProcesses.get(i).getArrivingTime() < min) {
                min = allProcesses.get(i).getArrivingTime();
                minArrivingTime = allProcesses.get(i);
            }
        }
        return minArrivingTime;
    }

    public Process getNextProcessAfterCurrentProcess(ArrayList<Process> allProcesses, int startTime, int endTime) {
        for (int i = 0; i < allProcesses.size(); i++) {
            Process p = allProcesses.get(i);
            if (p.getArrivingTime() > startTime && p.getArrivingTime() <= endTime) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<CPU> processEachMaximum(ArrayList<Process> allProcesses,
                                                          ArrayList<CPU> scheduledProcesses, int executionTime) {

        while (allProcesses.size() > 0) {
            Process maxProcess = this.getMaximumPriorityProcess(allProcesses);
            executionTime += maxProcess.getBurstTime();
            scheduledProcesses.add(new CPU(maxProcess.getProcessNumber(), executionTime));
            allProcesses.remove(maxProcess);
        }
        return scheduledProcesses;
    }

    public ArrayList<CPU> getCompletionTimes(ArrayList<Process> allProcesses,
                                                          ArrayList<CPU> scheduledProcesses) {

        ArrayList<CPU> scheduledProcessTime = new ArrayList<CPU>();

        for (Process p : allProcesses) {
            CPU sp = null;
            for (CPU scheduledProcess : scheduledProcesses) {
                if (scheduledProcess.getProcessNumber() == p.getProcessNumber()) {
                    sp = scheduledProcess;
                }
            }
            scheduledProcessTime.add(sp);
        }
        return scheduledProcessTime;
    }



    // TurnAroundTime is equal to CompletionTime - ArrivingTime
    public ArrayList<CPU> getTurnAroundTimes(ArrayList<Process> allProcesses,
                                                          ArrayList<CPU> completionTimes) {

        ArrayList<CPU> turnAroundTimes = new ArrayList<CPU>();

        for (int i = 0; i < completionTimes.size(); i++) {
            CPU scheduledProcess = new CPU(allProcesses.get(i).getProcessNumber(),
                    (completionTimes.get(i).getExeTime() - allProcesses.get(i).getArrivingTime()));
            turnAroundTimes.add(scheduledProcess);
        }
        return turnAroundTimes;
    }
    
    

    // WaintingTime = TurnAroundTime - BurstTime
    public ArrayList<CPU> getWaitingTimes(ArrayList<Process> allProcesses,
                                                       ArrayList<CPU> turnAroundTimes) {

        ArrayList<CPU> waitingTimes = new ArrayList<CPU>();

        for (int i = 0; i < allProcesses.size(); i++) {
            CPU waitingTime = new CPU(allProcesses.get(i).getProcessNumber(),
                    (turnAroundTimes.get(i).getExeTime() - allProcesses.get(i).getBurstTime()));

            waitingTimes.add(waitingTime);
        }
        return waitingTimes;
    }
}