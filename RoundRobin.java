public class RoundRobin {
    private Process processes[];
    int quantum_time;
    int temp;
    int count,sq; //temp and sq are temproray variables
    int remaining_burst_time[];
    int turnaround_times[];
    int waiting_times[];
    int total_waiting_time;
    int total_turnaround_time;
    public  RoundRobin(Process processes[],int quantum_time){
        this.quantum_time = quantum_time;
        this.processes = processes;
        remaining_burst_time = new int[processes.length];
        turnaround_times = new int[processes.length];
        waiting_times = new int[processes.length];
        for (int i = 0; i < processes.length; i++) {
            remaining_burst_time[i] = processes[i].bt;
        }
        while (true){
            for (int i = 0,count = 0; i < processes.length; i++) {
                temp = quantum_time;
                if (remaining_burst_time[i] == 0){
                    count++;
                    continue;
                }
                if (remaining_burst_time[i]>quantum_time)
                    remaining_burst_time[i] -= quantum_time;
                else{
                    if (remaining_burst_time[i]>=0){
                        temp = remaining_burst_time[i];
                        remaining_burst_time[i] = 0;
                    }
                    sq += temp;
                    turnaround_times[i] = sq;
                }
                if (processes.length == count)
                    break;
            }
            System.out.print("\nProcess\tBurst Time\tTurnaround Time\tWaiting Time\n");
            for (int i = 0; i < processes.length; i++) {
                waiting_times[i] = turnaround_times[i] - processes[i].bt;
                total_waiting_time+= waiting_times[i];
                total_turnaround_time += turnaround_times[i];
                System.out.print("\n  "+(i+1)+"\t  "+processes[i].bt+"\t\t   "+turnaround_times[i]+"\t\t   "+waiting_times[i]);
            }
            System.out.println("\nAverage waiting Time = "+total_waiting_time/processes.length);
            System.out.println("Average turnaround time = "+ total_turnaround_time/processes.length);
        }
    }
}
