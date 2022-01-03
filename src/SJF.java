import java.util.*;

public class SJF {
    private Process [] proc;

    public SJF(Process [] readyQueue){
        this.proc=readyQueue;
    }

    void sjfAlgorithm(){
        int n = proc.length;
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int rt[]=new int[n];
        int st=0, tot=0;
        int cpu_free_time=0;
        float cpuUtilazation=0;
        float avgwt=0, avgta=0,avgrt=0,throughput=0;

        while(true)
        {
            int c=proc.length, min=Integer.MAX_VALUE;
            if (tot == proc.length) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<proc.length; i++)
            {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((proc[i].art <= st) && (f[i] == 0) && (proc[i].bt<min))
                {
                    min=proc[i].bt;
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==proc.length)
            {
                st++;
                cpu_free_time++;
            }
            else
            {
                rt[c]=st;
                ct[c]=st+proc[c].bt;
                st+=proc[c].bt;
                ta[c]=ct[c]-proc[c].art;
                wt[c]=ta[c]-proc[c].bt;
                f[c]=1;
                tot++;
            }
        }
        System.out.println("\nSJF Scheduling Algorithm : \n");
        for(int i=0;i<proc.length;i++)
        {
            avgwt+= wt[i];
            avgta+= ta[i];
            avgrt+=rt[i];
            throughput +=this.proc[i].bt;
        }
        throughput=(float)this.proc.length/(throughput);
        cpuUtilazation=100-(cpu_free_time/(float)st)*100;
        OutPut npp = new OutPut((float)(avgwt/n),(float)(avgta/n),(float)(avgrt/n),throughput, cpuUtilazation);
        npp.showResult();

    }


}