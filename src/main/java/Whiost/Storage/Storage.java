package Whiost.Storage;

import Whiost.Task.*;
import Whiost.WhiostException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public TaskList load() throws WhiostException {
        TaskList tasks = new TaskList();
        try {
            if (!Files.exists(filePath)) return tasks;
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task task = parseTask(line);
                tasks.addTask(task);
            }
        } catch (Exception e) {
            throw new WhiostException("Failed to load tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void save(TaskList tasks) throws WhiostException {
        try {
            Files.createDirectories(filePath.getParent());
            List<String> lines = tasks.findTasks("").stream() // Get all tasks
                    .map(Task::toFileFormat)
                    .collect(Collectors.toList());
            Files.write(filePath, lines);
        } catch (Exception e) {
            throw new WhiostException("Failed to save tasks: " + e.getMessage());
        }
    }

    private Task parseTask(String line) throws WhiostException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new WhiostException("Corrupted task data");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = switch (type) {
            case "T" -> new Todo(description);
            case "D" -> new Deadline(description, parts.length > 3 ? parts[3] : "");
            case "E" -> new Event(description, parts.length > 3 ? parts[3] : "", parts.length > 4 ? parts[4] : "");
            default -> throw new WhiostException("Unknown task type: " + type);
        };

        if (isDone) task.markDone();
        return task;
    }
}