# Priority-Queue-Task-Manager-Java-

## Description:
This is a simple task manager application built in Java, which uses a `PriorityQueue` to manage tasks based on priority. The tasks are stored in a priority queue where the task with the highest priority (lowest priority number) is processed first.


This demo version allows the user to:
- Add tasks with descriptions and priorities.
- View the task queue sorted by priority.
- Remove the highest priority task from the queue.

## Features:
- **Add Task**: Users can input a task description and a priority (lower priority number = higher priority).
- **View Task Queue**: Displays all tasks currently in the queue in order of their priority.
- **Remove Highest Priority Task**: Removes the task with the highest priority (lowest priority number).
- **Input Validation**: Ensures that the task description is not empty and the priority is unique and falls within the allowed range.

## How to Use:
1. **Run the Program**: Compile and run the `PriorityQueueTaskManager` class.

`javac PriorityQueueTaskManager.java`

`java PriorityQueueTaskManager`

2. Add Task:

    Input task description and priority. If a priority is already assigned, you’ll be prompted to choose a different one.
3. View Task Queue:

    Displays the tasks in priority order.
4. Remove Task:

    Removes the highest priority task from the queue.

This task manager lets you add, view, and remove tasks based on priority, ensuring that the most important tasks are always handled first.