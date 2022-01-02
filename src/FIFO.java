public class FIFO {
    private Process processes[];
    private int waiting_times[];
    private int turnaround_times[];
    private int total_waiting_time = 0;
    private int total_turnaround_time = 0;

    public FIFO(Process processes[]) {
        this.processes = processes;
        CalculationProcess();
    }

    void CalculationProcess() {
        waiting_times = new int[this.processes.length];
        turnaround_times = new int[this.processes.length];
        CalculateWaitingTime(this.processes);
        CalculateTurnAroundTime(this.processes);
        CalculateTotalTimes();

    }

    private void CalculateTotalTimes() {
        System.out.println("Process\tBurst\tWaiting\tTurnAround");
        for (int i = 0; i < processes.length; i++) {
            total_waiting_time += waiting_times[i];
            total_turnaround_time += turnaround_times[i];

        }
        double average_waiting = total_waiting_time / processes.length;
        double average_turnaround = total_turnaround_time / processes.length;
        System.out.printf("Average waiting time = %f", average_waiting);
        System.out.printf("\n");
        System.out.printf("Average turn around time = %d ", average_turnaround);
    }

    private void CalculateTurnAroundTime(Process[] processes) {
        //turnaround time = waiting_time + burst_time
        for (int i = 0; i < processes.length; i++) {
            turnaround_times[i] = processes[i].bt + waiting_times[i];
        }
    }

    void CalculateWaitingTime(Process[] processes) {
        waiting_times[0] = 0; // the waiting time of the first process is eaual to 0
        for (int i = 1; i < processes.length; i++) {
            waiting_times[i] = processes[i - 1].bt + waiting_times[i - 1];
        }
    }
}
