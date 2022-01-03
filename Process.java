class Process {
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time
    int priority;

    public Process(int pid, int bt, int art, int priority)
    {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
        this.priority=priority;
    }
}
