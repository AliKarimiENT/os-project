public class Main {

  public static void main(String[] args) {
    int[] bt =  {3,6,1,2,4}; // burst times
    int[] at =  {0, 1,3,2,4}; // arrival times
    int[] pp = {3,4,9,7,8};  // process priority
    Process[] processes = new Process[bt.length];
    for (int i = 0; i < processes.length; i++) {
      processes[i] = new Process(i + 1, bt[i],0);

      processes[i] = new Process(i + 1, bt[i], at[i]);
      processes[i].setPriority(pp[i]);
    }

    FCFS fcfs = new FCFS(processes);
    fcfs.printResult();

    SJF sjf = new SJF(processes);
    sjf.printResult();

    SJFPreemptive sjf_preemptive = new SJFPreemptive(processes);
    sjf_preemptive.printResult();


    Priority priority = new Priority(processes);
    priority.printResult();
  }
}
