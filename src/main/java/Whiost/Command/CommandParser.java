package Whiost.Command;

import Whiost.WhiostException;

public class CommandParser {
    public static Command parse(String input) throws WhiostException {
        if (input == null || input.trim().isEmpty()) {
            throw new WhiostException("Input cannot be empty");
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        return switch (command) {
            case "bye" -> new Command(CommandType.BYE, new String[0]);
            case "list" -> new Command(CommandType.LIST, new String[0]);
            case "todo" -> parseTodo(args);
            case "deadline" -> parseDeadline(args);
            case "event" -> parseEvent(args);
            case "mark" -> parseIndexCommand(args, CommandType.MARK);
            case "unmark" -> parseIndexCommand(args, CommandType.UNMARK);
            case "delete" -> parseIndexCommand(args, CommandType.DELETE);
            case "find" -> parseFind(args);
            default -> throw new WhiostException("I don't understand that command");
        };
    }

    private static Command parseTodo(String args) throws WhiostException {
        if (args.isBlank()) throw new WhiostException("Todo description empty");
        return new Command(CommandType.TODO, new String[]{args});
    }
    private static Command parseDeadline(String args) throws WhiostException {
        if (args.isBlank()) throw new WhiostException("Deadline description empty");

        String[] parts = args.split(" /by ", 2);
        if (parts.length < 2) {
            throw new WhiostException("Deadline requires '/by' time");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();
        return new Command(CommandType.DEADLINE, new String[]{description, by});
    }

    private static Command parseEvent(String args) throws WhiostException {
        if (args.isBlank()) throw new WhiostException("Event description empty");

        String[] parts = args.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new WhiostException("Event requires '/from' and '/to' times");
        }

        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new Command(CommandType.EVENT, new String[]{description, from, to});
    }

    private static Command parseMark(String args) throws WhiostException {
        return parseIndexCommand(args, CommandType.MARK);
    }

    private static Command parseUnmark(String args) throws WhiostException {
        return parseIndexCommand(args, CommandType.UNMARK);
    }

    private static Command parseDelete(String args) throws WhiostException {
        return parseIndexCommand(args, CommandType.DELETE);
    }

    private static Command parseFind(String args) throws WhiostException {
        if (args.isBlank()) throw new WhiostException("Find keyword empty");
        return new Command(CommandType.FIND, new String[]{args.trim()});
    }

    private static Command parseIndexCommand(String args, CommandType type)
            throws WhiostException {
        if (args.isBlank()) throw new WhiostException("Missing task index");

        try {
            int index = Integer.parseInt(args.trim()) - 1;
            return new Command(type, new String[]{String.valueOf(index)});
        } catch (NumberFormatException e) {
            throw new WhiostException("Invalid task index");
        }
    }
}
