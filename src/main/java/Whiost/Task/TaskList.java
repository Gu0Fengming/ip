package Whiost.Task;

import Whiost.WhiostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) throws WhiostException {
        if (index < 0 || index >= tasks.size()) {
            throw new WhiostException("Task index out of bounds");
        }
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws WhiostException {
        if (index < 0 || index >= tasks.size()) {
            throw new WhiostException("Task index out of bounds");
        }
        tasks.remove(index);
    }

    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    public String formatTaskList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    public void markTask(int index, boolean isDone) throws WhiostException {
        Task task = getTask(index);  // Reuse existing bounds checking
        if (isDone) {
            task.markDone();
        } else {
            task.unmarkDone();
        }
    }
}