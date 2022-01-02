import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class NPP
{
    private Process [] proc;

    public NPP(Process [] readyQueue){
        this.proc=readyQueue;
    }

    void sortAccordingArrivalTimeAndPriority(int[] at, int[] bt, int[] prt, int[] pid)
    {

        int temp;
        int stemp;
        for (int i = 0; i < this.proc.length; i++)
        {

            for (int j = 0; j < this.proc.length - i - 1; j++)
            {
                if (at[j] > at[j + 1])
                {
                    //swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    //swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    //swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    //swapping process identity
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;

                }
                //sorting according to priority when arrival timings are same
                if (at[j] == at[j + 1])
                {
                    if (prt[j] > prt[j + 1])
                    {
                        //swapping arrival time
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                        //swapping burst time
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        //swapping priority
                        temp = prt[j];
                        prt[j] = prt[j + 1];
                        prt[j + 1] = temp;

                        //swapping process identity
                        stemp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = stemp;

                    }
                }
            }

        }
    }

    void priorityNonPreemptiveAlgorithm()
    {


        int [] bt=new int[this.proc.length];
        int [] at=new int[this.proc.length];
        int [] prt=new int[this.proc.length];
        int [] pid=new int[this.proc.length];

        for (int i = 1; i < this.proc.length; i++){
            bt[i]=this.proc[i].bt;
            at[i]=this.proc[i].art;
            prt[i]=this.proc[i].priority;
            pid[i]=this.proc[i].pid;
        }

        int finishTime[] = new int[this.proc.length];
        int waitingTime[] = new int[this.proc.length];
        int turnAroundTime[] = new int[this.proc.length];
        int responseTime[] = new int[this.proc.length];

        sortAccordingArrivalTimeAndPriority(at, bt, prt, pid);

        //calculating waiting & turn-around time for each process
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];
        responseTime[0]=0;

        for (int i = 1; i < this.proc.length; i++)
        {
            finishTime[i] = bt[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
            responseTime[i]=finishTime[i - 1];
        }

        float averageWaitingTime = 0;
        float averageTurnAroundTime = 0;
        float averageResponse=0;
        float throghput =0;
        int totalBrustTime=0;

        for (int i=0;i<this.proc.length;i++){
            averageWaitingTime+=waitingTime[i];
            averageTurnAroundTime+=turnAroundTime[i];
            averageResponse+=responseTime[i];
            totalBrustTime +=this.proc[i].bt;
        }

        averageResponse=averageResponse/this.proc.length;
        averageWaitingTime=averageWaitingTime/this.proc.length;
        averageTurnAroundTime=averageTurnAroundTime/this.proc.length;
        throghput=(float)this.proc.length/(totalBrustTime);

        System.out.println("Non-preemptive Priority Scheduling Algorithm : \n");
        OutPut npp = new OutPut(averageWaitingTime,averageTurnAroundTime,averageResponse,throghput, (float) 20.1);
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
