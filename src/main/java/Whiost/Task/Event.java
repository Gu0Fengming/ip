package Whiost.Task;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description
                + " (from: " + from + " to: " + to + ")";
    }
}