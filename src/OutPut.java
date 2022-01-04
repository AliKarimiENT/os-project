public class OutPut {
    private float avgWaitingTime;
    private float avgTurnAroundTime;
    private float avgResponseTime;
    private float throughput;
    private float cpuUtilization;
    private float spendTime;

    public OutPut(float avgWaitingTime,float avgTurnAroundTime, float avgResponseTime,float throughput,float cpuUtilization,float spendTime){
        this.avgWaitingTime=avgWaitingTime;
        this.avgTurnAroundTime=avgTurnAroundTime;
        this.avgResponseTime=avgResponseTime;
        this.throughput=throughput;
        this.cpuUtilization=cpuUtilization;
        this.spendTime=spendTime;
    }
    void showResult(){
        System.out.println( "average Waiting Time:  "+this.avgWaitingTime+ "     average turn around time:  "+this.avgTurnAroundTime);
        System.out.println("average response time:  "+this.avgResponseTime+"    Throughput:  "+this.throughput);
        System.out.println("CPU utilization:  "+this.cpuUtilization+"%"+"     spend time:   "+this.spendTime+" ms");
    }
}
