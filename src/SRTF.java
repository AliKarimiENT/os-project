
public class SRTF
{
    private Process [] proc;

    public SRTF(Process [] readyQueue){
        this.proc=readyQueue;
    }
    static int [] findWaitingTime(Process[] proc, int n,
                                int[] wt,int[]resT)
    {
        int[] rt = new int[n];
        int cpu_free_time=0; int t=0;
        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;

        int complete = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets
        // completed
        while (complete != n) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++)
            {
                if ((proc[j].art <= t) &&
                        (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                    resT[j]=t;
                }
            }

            if (!check) {
                t++;
                cpu_free_time++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time -
                        proc[shortest].bt -
                        proc[shortest].art;

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
        int [] result={t,cpu_free_time};
        return result;
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Process[] proc, int n,
                                   int[] wt, int[] tat)
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }

    // Method to calculate average time
    void findavgTime()
    {
        int n =this.proc.length;
        int[] wt = new int[n], tat = new int[n];
        int total_wt = 0, total_tat = 0;
        int [] resTime=new int[n];
        int total_rt=0;
        float throughput=0;
        float cpuUtilazation=0;
        float spendTime=0;

        // Function to find waiting time of all
        // processes
        int time []=findWaitingTime(this.proc, n, wt,resTime);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(this.proc, n, wt, tat);


        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            total_rt+=resTime[i];
            throughput+=proc[i].bt;
        }
        throughput=(float)this.proc.length/(throughput);
        cpuUtilazation=100-(time[1]/(float)time[0])*100;
        spendTime=time[0]*getUnitTime()/1000000;

        System.out.println("\nSRTF Scheduling Algorithm : \n");

        OutPut npp = new OutPut((float)total_wt / (float)n,(float)total_tat / (float)n,(float)total_rt / (float)n,throughput,cpuUtilazation,spendTime);
        npp.showResult();

    }
    long getUnitTime(){
        long start1 = System.nanoTime();
        int temp =0;
        for (int i=0; i < 10000; i++)
            if (i % 2 == 0)
                temp= i/2;
            else
                temp=2*i;

        long end1 = System.nanoTime();
        return end1-start1;
    }

}
