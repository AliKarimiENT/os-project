import java.util.Scanner;

public class FIFO
{
    private Process [] proc;

    public FIFO(Process [] readyQueue){
        this.proc=readyQueue;
    }



    void sortAccordingArrivalTime(int[] at, int[] bt, int[] pid)
    {
        boolean swapped;
        int temp;
        int stemp;
        for (int i = 0; i <this.proc.length ; i++)
        {
            swapped = false;
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

                    //swapping process id
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;

                    //enhanced bubble sort
                    swapped = true;
                }
            }
            if (swapped == false)
            {
                break;
            }
        }
    }

    void firstComeFirstServeAlgorithm()
    {
        int finishTime[] = new int[this.proc.length];
        int bt[] = new int[this.proc.length];
        int at[] = new int[this.proc.length];
        int pid[] = new int[this.proc.length];
        int waitingTime[] = new int[this.proc.length];
        int turnAroundTime[] = new int[this.proc.length];
        int responseTime[]=new int[this.proc.length];
        int cpu_free_time=0;


        for (int i = 0; i <= this.proc.length-1; i++){
            bt[i]=this.proc[i].bt;
            at[i]=this.proc[i].art;
            pid[i]=this.proc[i].pid;
        }


        sortAccordingArrivalTime(at, bt, pid);

        //calculating waiting & turn-around time for each process
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];
        responseTime[0]=at[0];

        if(at[0]>0){
            cpu_free_time=at[0];
        }



        for (int i = 1; i < this.proc.length; i++)
        {



            if(at[i]>finishTime[i-1]){
                cpu_free_time += at[i] - finishTime[i-1];
                responseTime[i]=at[i];
                finishTime[i] = bt[i] + at[i];
            }
            else {
                responseTime[i]=finishTime[i-1];
                finishTime[i] = bt[i] + finishTime[i - 1];
            }

            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
        }

        float averageWaitingTime =0;
        float averageTurnAroundTime = 0;
        float averageResponseTime=0;
        float throughput=0;
        float totalFinishTime=0;
        float cpuUtilazation=0;
        float spendTime=0;

        for (int i = 0; i < this.proc.length; i++){
            averageWaitingTime+=waitingTime[i];

            averageTurnAroundTime+=turnAroundTime[i];
            averageResponseTime+=waitingTime[i];
            throughput+=this.proc[i].bt;

        }
        totalFinishTime+=finishTime[this.proc.length-1];

        averageWaitingTime=averageWaitingTime/this.proc.length;
        averageTurnAroundTime=averageTurnAroundTime/this.proc.length;
        averageResponseTime=averageResponseTime/this.proc.length;
        throughput=this.proc.length/throughput;
        cpuUtilazation=100-(cpu_free_time/totalFinishTime)*100;
        spendTime=finishTime[this.proc.length-1]*getUnitTime()/1000000;

        System.out.println("first come first serve Scheduling Algorithm : \n");
        OutPut npp = new OutPut(averageWaitingTime,averageTurnAroundTime,averageResponseTime,throughput, cpuUtilazation,spendTime);
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
