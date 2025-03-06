import java.util.PriorityQueue;

public class PriorityQueueTaskManager {
    public static void main(String[] args) {
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();

        taskQueue.add(new Task("Finish Report", 2));
        taskQueue.add(new Task("Submit Assignment", 1));
        taskQueue.add(new Task("Prepare for Interview", 3));

        while (!taskQueue.isEmpty()) {
            System.out.println("Processing Task: " + taskQueue.poll().description);
        }
    }
}

class Task implements Comparable<Task> {
    String description;
    int priority;

    Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(other.priority, this.priority);
    }
}