import java.util.Comparator;

public class PriorityComparator implements Comparator<Process> {

  @Override
  public int compare(Process p1, Process p2) {
    if (p1.getArrivingTime() < p2.getArrivingTime())
      return (-1);

    else if (p1.getArrivingTime() == p2.getArrivingTime() && p1.getPriority() > p2.getPriority())
      return (-1);

    else
      return (1);
  }
}
