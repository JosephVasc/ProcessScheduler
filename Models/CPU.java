  
package Models;

public class CPU {

    private int number;
    private int exeTime;
    private Process process;

    public CPU(int processNumber, int exeTime) {
        this.number = processNumber;
        this.exeTime = exeTime;
    }
    public int getProcessNumber() {
        return number;
    }

    public void setProcessNumber(int processNumber) {
        this.number = processNumber;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }
}