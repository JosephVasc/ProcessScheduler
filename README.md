# ProcessScheduler
The object is to simulate the scheduler(using a preemptive priority based algorithm) of
a simple operating system. The computer will have two resources , a CPU and a disk.
There will also be an undetermined number of jobs in the system. Every job will have
the following attributes:
 job number - these are assigned consecutively and uniquely
 priority number - these take values 1 through 4, (1 being the highest, 2, 3, and 4)
 maximum time remaining - initially equal to the job’s time estimate
Every job will be in exactly one of three states at all times:
 executing on the CPU - there is at most one of these jobs
 sitting in the ready queue(waiting to run) - jobs will be chosen to move to the cpu
based on a
 priority value = priority number
 sitting in the wait queue - (waiting for disk I/O) 
In a real computer system, movement between these three states is governed by the
actual instructions in the job ( they issue their own I/O requests). We will simulate this
with a file of commands. There will be five different commands ( named by a single
character ):

