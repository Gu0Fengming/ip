package Whiost.Ui;

import Whiost.Task.*;
import java.util.List;

public class Ui {
    public String getGreeting() {
        return "Hello! I'm Whiost\nWhat can I do for you?";
    }

    public String getGoodbyeMessage() {
        return "Goodbye! Hope to see you again soon!";
    }

    public String getAddTaskMessage(Task task, int totalTasks) {
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task, totalTasks
        );
    }

    public String getDeleteMessage(Task task, int remainingTasks) {
        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task, remainingTasks
        );
    }

    public String showError(String message) {
        return "OOPS!!! " + message;
    }

    public String showReminders(TaskList tasks) {
        return "Reminders:\n" + tasks.formatTaskList();
    }
}