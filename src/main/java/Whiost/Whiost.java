package Whiost;

import Whiost.Command.*;
import Whiost.Task.*;
import Whiost.Storage.*;
import Whiost.Ui.*;
import java.util.List;

public class Whiost {
    private final Ui ui = new Ui();
    private final Storage storage;
    private final TaskList tasks;

    public Whiost(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = loadTasks();
    }

    private TaskList loadTasks() {
        try {
            return storage.load();
        } catch (WhiostException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }

    public String getGreeting() {
        return ui.getGreeting() + (tasks.isEmpty() ? "" : "\n" + ui.showReminders(tasks));
    }

    public String handleInput(String input) {
        try {
            Command command = CommandParser.parse(input);
            return executeCommand(command);
        } catch (WhiostException e) {
            return ui.showError(e.getMessage());
        }
    }

    private String executeCommand(Command command) throws WhiostException {
        return switch (command.getType()) {
            case BYE -> handleBye();
            case LIST -> handleList();
            case TODO -> handleTodo(command.getArgs());
            case DEADLINE -> handleDeadline(command.getArgs());
            case EVENT -> handleEvent(command.getArgs());
            case MARK -> handleMark(command.getArgs());
            case UNMARK -> handleUnmark(command.getArgs());
            case DELETE -> handleDelete(command.getArgs());
            case FIND -> handleFind(command.getArgs());
            default -> throw new WhiostException("Unknown command");
        };
    }

    // Command Handlers
    private String handleBye() {
        try {
            storage.save(tasks);
            return ui.getGoodbyeMessage();
        } catch (WhiostException e) {
            return ui.showError("Failed to save tasks: " + e.getMessage());
        }
    }

    private String handleList() {
        if (tasks.isEmpty()) {
            return ui.showError("There are no tasks in your list");
        }
        return "Here are your tasks:\n" + tasks.formatTaskList();
    }

    private String handleTodo(String[] args) throws WhiostException {
        if (args.length < 1 || args[0].isEmpty()) {
            throw new WhiostException("Todo description cannot be empty");
        }
        Task task = new Todo(args[0]);
        tasks.addTask(task);
        return ui.getAddTaskMessage(task, tasks.size());
    }

    private String handleDeadline(String[] args) throws WhiostException {
        if (args.length < 2 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new WhiostException("Invalid deadline format. Use: deadline <description> /by <time>");
        }
        Task task = new Deadline(args[0], args[1]);
        tasks.addTask(task);
        return ui.getAddTaskMessage(task, tasks.size());
    }

    private String handleEvent(String[] args) throws WhiostException {
        if (args.length < 3 || args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty()) {
            throw new WhiostException("Invalid event format. Use: event <description> /from <time> /to <time>");
        }
        Task task = new Event(args[0], args[1], args[2]);
        tasks.addTask(task);
        return ui.getAddTaskMessage(task, tasks.size());
    }

    private String handleMark(String[] args) throws WhiostException {
        int index = validateAndParseIndex(args);
        tasks.markTask(index, true);
        return "Nice! I've marked this task as done:\n  " + tasks.getTask(index);
    }

    private String handleUnmark(String[] args) throws WhiostException {
        int index = validateAndParseIndex(args);
        tasks.markTask(index, false);
        return "OK, I've unmarked this task:\n  " + tasks.getTask(index);
    }

    private String handleDelete(String[] args) throws WhiostException {
        int index = validateAndParseIndex(args);
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        return ui.getDeleteMessage(task, tasks.size());
    }

    private String handleFind(String[] args) throws WhiostException {
        if (args.length < 1 || args[0].isEmpty()) {
            throw new WhiostException("Find command requires a search keyword");
        }
        List<Task> results = tasks.findTasks(args[0]);
        if (results.isEmpty()) {
            return ui.showError("No tasks found matching '" + args[0] + "'");
        }
        return formatSearchResults(results);
    }

    // Helper Methods
    private int validateAndParseIndex(String[] args) throws WhiostException {
        if (args.length < 1) {
            throw new WhiostException("Missing task index");
        }

        try {
            int index = Integer.parseInt(args[0]);
            if (index < 0 || index >= tasks.size()) {
                throw new WhiostException("Task index out of bounds");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new WhiostException("Invalid task number format");
        }
    }

    private String formatSearchResults(List<Task> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks:\n");
        for (int i = 0; i < results.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(results.get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    public Ui getUi() {
        return ui;
    }
}