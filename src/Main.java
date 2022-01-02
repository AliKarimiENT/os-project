public class Main {
    public static void main(String[] args) {
        Process processes[] = new Process[3];
        processes[0] = new Process(1,10,0,0);
        processes[1] = new Process(2,5,0,0);
        processes[2] = new Process(3,8,0,0);
        FIFO fifo_alg = new FIFO(processes);
    }
}
