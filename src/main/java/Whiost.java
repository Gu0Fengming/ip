import java.util.Scanner;
import java.util.ArrayList;

public class Whiost {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Whiost\nWhat can I do for you?\n";
        String addTask = "Got it. I've added this task:";
        String marked = "Nice! I've marked this task as done:";
        String unmarked = "OK, I've marked this task as not done yet:";
        String reportTask1 = "Now you have ";
        String reportTask2 = " tasks in the list.";

        System.out.print(greeting);

        boolean p = true;
        ArrayList<String> lst = new ArrayList<>();
        ArrayList<String> typeLst = new ArrayList<>();
        ArrayList<String> markLst = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (p) {
            String inp = scanner.nextLine();
            if (inp.equals("bye")) {
                p = false;
                System.out.println("Bye. Hope to see you again soon!\n");
            } else if (inp.equals("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.printf("%d.%s%s %s%n", (i + 1), typeLst.get(i), markLst.get(i), lst.get(i));
                }
            } else if (inp.startsWith("todo ")) {
                System.out.println(addTask);
                typeLst.add("[T]");
                markLst.add("[ ]");
                lst.add(inp.substring(5));
                System.out.println("  [T][ ] " + inp.substring(5));
                System.out.println(reportTask1 + lst.size() + reportTask2);
            } else if (inp.startsWith("deadline ")) {
                System.out.println(addTask);
                typeLst.add("[D]");
                markLst.add("[ ]");
                int pos = inp.indexOf('/');
                lst.add(inp.substring(9, pos) + "(" + inp.substring(pos + 1) + ")");
                System.out.println("  [D][ ] " + inp.substring(9, pos) + "(" + inp.substring(pos + 1) + ")");
                System.out.println(reportTask1 + lst.size() + reportTask2);
            } else if (inp.startsWith("event ")) {
                System.out.println(addTask);
                typeLst.add("[E]");
                markLst.add("[ ]");
                int pos1 = inp.indexOf('/');
                int pos2 = pos1 + inp.substring(pos1 + 1).indexOf('/');
                lst.add(inp.substring(6, pos1) + "(from:" + inp.substring(pos1 + 5, pos2) + " to:" + inp.substring(pos2 + 4) + ")");
                System.out.println("  [E][ ] " + inp.substring(6, pos1) + "(from:" + inp.substring(pos1 + 5, pos2) + " to:" + inp.substring(pos2 + 4) + ")");
                System.out.println(reportTask1 + lst.size() + reportTask2);
            } else if (inp.startsWith("mark ")) {
                System.out.println(marked);
                int pos = Integer.parseInt(inp.substring(5)) - 1;
                markLst.set(pos, "[X]");
                System.out.println("  " + typeLst.get(pos) + markLst.get(pos) + " " + lst.get(pos));
            } else if (inp.startsWith("unmark ")) {
                System.out.println(unmarked);
                int pos = Integer.parseInt(inp.substring(7)) - 1;
                markLst.set(pos, "[ ]");
                System.out.println("  " + typeLst.get(pos) + markLst.get(pos) + " " + lst.get(pos));
            }
        }
    }
}
