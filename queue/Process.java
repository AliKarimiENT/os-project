package com.ma;

class Process {
    int processId;
    int burstTime;
    int arrivalTime;
    int priority;

    public Process(int processId, int burstTime, int arrivalTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority=priority;
    }
}
