public class OutPut {
    private float avgWaitingTime;
    private float avgTurnAroundTime;
    private float avgResponseTime;
    private float throughput;
    private float cpuUtilization;

    public OutPut(float avgWaitingTime,float avgTurnAroundTime, float avgResponseTime,float throughput,float cpuUtilization){
        this.avgWaitingTime=avgWaitingTime;
        this.avgTurnAroundTime=avgTurnAroundTime;
        this.avgResponseTime=avgResponseTime;
        this.throughput=throughput;
        this.cpuUtilization=cpuUtilization;
    }
    void showResult(){
        System.out.println( "average Waiting Time:  "+this.avgWaitingTime+ "    average turn around time:  "+this.avgTurnAroundTime);
        System.out.println("average response time:  "+this.avgResponseTime+"   Throughput:  "+this.throughput);
    }
}
