package com.ma;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Queue queue = new Queue();
        List<Process> readyQueue = queue.getReadyQueue$os();
        readyQueue.remove(0);
        readyQueue.remove(1);
        readyQueue.remove(2);

        queue.completeReadyQueue$os(readyQueue);


    }
}
