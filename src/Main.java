public class Main {
    public static void main(String[] args){
        Process[] readyQueue = {
                new Process(1, 1, 1,5),
                new Process(2, 8, 4,2),
                new Process(3, 7, 5,4),
                new Process(4, 3, 8,3)
        };

        //Non-preemptive Priority
        NPP npp = new NPP(readyQueue);
        npp.priorityNonPreemptiveAlgorithm();

        // short job first
        SJF sjf=new SJF(readyQueue);
        sjf.sjfAlgorithm();

        // non-preemptive short job first
        SRTF srtf=new SRTF(readyQueue);
        srtf.findavgTime();

    }
}
