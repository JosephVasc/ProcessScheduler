package Models;

public class Process {

    private int number;
    private int priority;
    private int arrivingTime;
    private int burstTime;
    private String jobName;
    private int idleTime = 0;
    

    public Process() 
    {
        this.number = 0;
        this.priority = 0;
        this.arrivingTime = 0;
        this.burstTime = 0;
        this.jobName = null;
       
    }

    public Process(int processNumber, int processPriority, int arrivingTime, int burstTime, String name) 
    {
        this.number = processNumber;
        this.priority = processPriority;
        this.arrivingTime = arrivingTime;
        this.burstTime = burstTime;
        this.jobName = name;
       
    }

    public int getProcessNumber() 
    {
        return number;
    }

    public void setProcessNumber(int processNumber) 
    {
        this.number = processNumber;
    }

    public int getProcessPriority() 
    { 
       
        return priority;
    }

    public void setProcessPriority(int processPriority) 
    {
        this.priority = processPriority;
    }

    public int getArrivingTime() 
    {
        return arrivingTime;
    }

    public void setArrivingTime(int arrivingTime) 
    {
        this.arrivingTime = arrivingTime;
    }

    public int getBurstTime() 
    {
        return burstTime;
    }
    public void setCommandName(String name)
    {
      this.jobName = name;
    }
    public String getCommandName()
    {
      return jobName;
    }

    public void setBurstTime(int burstTime) 
    {
        this.burstTime = burstTime;
    }
    public void incrementIdleTime()
    {
      this.idleTime++;
    }
}