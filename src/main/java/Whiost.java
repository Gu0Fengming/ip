import java.util.Scanner;
import java.util.ArrayList;

public class Whiost {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Whiost\nWhat can I do for you?\n";
        String marked = "Nice! I've marked this task as done:";
        String unmarked = "OK, I've marked this task as not done yet:";
        System.out.println(greeting);

        boolean p = true;
        ArrayList<String> lst = new ArrayList<>();
        ArrayList<String> markLst = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (p) {
            String inp = scanner.nextLine();
            if (inp.equals("bye")) {
                p = false;
                System.out.println("Bye. Hope to see you again soon!\n");
            } else if (inp.equals("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.printf("%d. %s%s%n", i + 1, markLst.get(i), lst.get(i));
                }
            } else {
                boolean p1 = true;
                for (int i = 0; i < lst.size(); i++) {
                    if (inp.equals(String.format("mark %d", (i + 1)))) {
                        System.out.println(marked);
                        markLst.set(i, "[X]");
                        System.out.println(markLst.get(i) + lst.get(i));
                        p1 = false;
                        break;
                    }
                    if (inp.equals(String.format("unmark %d", (i + 1)))) {
                        System.out.println(unmarked);
                        markLst.set(i, "[ ]");
                        System.out.println(markLst.get(i) + lst.get(i));
                        p1 = false;
                        break;
                    }
                }
                if (p1) {
                    lst.add(inp);
                    markLst.add("[ ]");
                    System.out.println("added: " + inp);
                }
            }
        }

    }
}
