/**
 * Author: Luke Filicetti
 * 
 * Description:
 * This is a task manager program that uses a Priority Queue to manage tasks with different priority levels.
 * The tasks are ordered based on their priority, where lower numbers represent higher priority. 
 * Users can add tasks with descriptions and priorities, view the current tasks in priority order, 
 * and remove the task with the highest priority. 
 * The program ensures that the task description is not empty and that priorities are unique and fall within a given range.
 * 
 * Features:
 * - Add a task with a description and priority.
 * - View the task queue sorted by priority.
 * - Remove the highest priority task.
 * - User input validation for valid integers and unique priorities.
 */

import java.util.*;
import java.io.*;

public class PriorityQueueTaskManager {
    private static final int MIN_PRIORITY = 1; // Minimum allowed priority value
    private static final int MAX_PRIORITY = 100; // Maximum allowed priority value
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        Set<Integer> usedPriorities = new HashSet<>(); // Track used priority levels

        while (true) {
            // Display options to the user
            System.out.println("\nTask Manager");
            System.out.println("1. Add Task");
            System.out.println("2. View Task Queue");
            System.out.println("3. Remove Highest Priority Task");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            
            int choice = getIntInput(scanner, 1, 4);
            scanner.nextLine(); // Clear the newline character after reading the integer

            switch (choice) {
                case 1:
                    // Add a new task
                    addTask(scanner, taskQueue, usedPriorities);
                    break;
                case 2:
                    // View all tasks in the priority queue
                    viewTaskQueue(taskQueue);
                    break;
                case 3:
                    // Remove the highest priority task
                    removeHighestPriorityTask(taskQueue, usedPriorities);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Add task to the queue with description and priority
    private static void addTask(Scanner scanner, PriorityQueue<Task> taskQueue, Set<Integer> usedPriorities) {
        // Prompt for task description
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();
        
        // Validate description isn't empty
        while (description.isEmpty()) {
            System.out.println("Task description cannot be empty.");
            System.out.print("Enter task description: ");
            description = scanner.nextLine().trim();
        }
    
        // Prompt for priority with validation for unique priority and valid range
        int priority;
        boolean validPriority = false;
        
        do {
            System.out.printf("Enter priority (%d-%d, lower number = higher priority): ", MIN_PRIORITY, MAX_PRIORITY);
            priority = getIntInput(scanner, MIN_PRIORITY, MAX_PRIORITY);
            scanner.nextLine(); // Clear the newline character
            
            // Check if the priority is already in use
            if (usedPriorities.contains(priority)) {
                System.out.println("Priority " + priority + " is already assigned to another task.");
                System.out.println("Please remove the existing task at this priority level or choose a different priority.");
            } else {
                validPriority = true;
            }
        } while (!validPriority);
    
        // Add the new priority to the set of used priorities
        usedPriorities.add(priority);
        
        // Create and add the task to the priority queue
        taskQueue.add(new Task(description, priority));
        System.out.println("Task \"" + description + "\" added with priority " + priority + ".");
    }

    // View tasks in the priority queue
    private static void viewTaskQueue(PriorityQueue<Task> taskQueue) {
        if (taskQueue.isEmpty()) {
            System.out.println("No tasks in the queue.");
        } else {
            System.out.println("\nTask Queue:");
            System.out.println("+---------------------------+------------+");
            System.out.println("| Description               | Priority   |");
            System.out.println("+---------------------------+------------+");
            
            // Create a copy of the queue to avoid modifying the original
            PriorityQueue<Task> tempQueue = new PriorityQueue<>(taskQueue);
            
            // Display tasks in priority order
            while (!tempQueue.isEmpty()) {
                Task task = tempQueue.poll();
                System.out.printf("| %-25s | %-10d |%n", task.description, task.priority);
            }
            
            System.out.println("+---------------------------+------------+");
        }
    }

    // Remove the task with the highest priority
    private static void removeHighestPriorityTask(PriorityQueue<Task> taskQueue, Set<Integer> usedPriorities) {
        if (taskQueue.isEmpty()) {
            System.out.println("No tasks to remove.");
        } else {
            Task removedTask = taskQueue.poll();
            
            // Remove the priority from the set of used priorities
            usedPriorities.remove(removedTask.priority);
            
            System.out.println("Removed task: \"" + removedTask.description + "\" with priority " + removedTask.priority + ".");
        }
    }

    // Get a valid integer input from the user within the specified range
    private static int getIntInput(Scanner scanner, int min, int max) {
        int input = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                if (scanner.hasNextInt()) {
                    input = scanner.nextInt();
                    
                    // Check if input is within the allowed range
                    if (input < min || input > max) {
                        System.out.printf("Please enter a number between %d and %d: ", min, max);
                    } else {
                        valid = true;
                    }
                } else {
                    System.out.println("Invalid input! Please enter a valid integer.");
                    scanner.next(); // Clear invalid input
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
        
        return input;
    }
}

// Task class implements Comparable to ensure tasks are ordered by priority
class Task implements Comparable<Task> {
    String description;
    int priority;

    Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        // Compare priorities (lower number = higher priority)
        return Integer.compare(this.priority, other.priority);
    }
}