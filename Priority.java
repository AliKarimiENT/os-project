import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class Priority {
    public Priority(LinkedList<Process> processes) {
        GanttChartProcess(processes);
    }

    private void GanttChartProcess(LinkedList<Process> processes) {
        int time = 0;
        TreeSet sorted = new TreeSet(new Comparator()); // sorted priority queue according to arrival time or priority
        LinkedList<GanttChart> result = new LinkedList<GanttChart>(); // this is for storing processes data
        while (processes.size() > 0)
            sorted.add(processes.removeFirst());

        Iterator iterator = sorted.iterator();

        // setting time to first process
        time = ((Process) sorted.first()).art;

        // scheduling the process
        while (iterator.hasNext()) {
            Process process = (Process) iterator.next();
            GanttChart ganttChart = new GanttChart();
            ganttChart.process_number = process.pid;
            ganttChart.start_time = time;
            time += process.bt;
            ganttChart.complete_time = time;
            ganttChart.turnaround_time = ganttChart.complete_time - process.art;
            ganttChart.waiting_time = ganttChart.turnaround_time - process.bt;
            result.add(ganttChart);
        }
        System.out.println("Process\tBurst\tWaiting\tTurnAround\tPriority");
        double total_waiting = 0;
        double total_turn_around = 0;
        double total_response = 0;
        for (GanttChart ganttChart : result) {
            System.out.println(String.format("P%-6d\t%-5d\t%-8d\t%-8d\t%-6d",
                    ganttChart.process_number, processes.get(result.indexOf(ganttChart)).bt, ganttChart.waiting_time, ganttChart.turnaround_time));
            total_waiting += ganttChart.waiting_time;
            total_turn_around += ganttChart.turnaround_time;
            total_response += ganttChart.complete_time - ganttChart.start_time;
        }
        System.out.println("Average waiting time " + total_waiting / result.size());
        System.out.println("Average Turnaround time " + total_turn_around/result.size());
        System.out.println("Average Response time " + total_response/ result.size());
    }
}

class GanttChart {
    int process_number, start_time, complete_time, turnaround_time, waiting_time;
}

class Comparator implements java.util.Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Process p1 = (Process) o1;
        Process p2 = (Process) o2;
        if (p1.art < p2.art)
            return (-1);
        else if (p1.art == p2.art && p1.priority > p2.priority)
            return (-1);
        else
            return (1);
    }
}