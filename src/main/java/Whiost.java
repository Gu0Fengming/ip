import java.util.Scanner;
import java.util.ArrayList;

public class Whiost {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Whiost\nWhat can I do for you?\n";
        System.out.println(greeting);

        boolean p = true;
        ArrayList<String> lst = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (p) {
            String inp = scanner.nextLine();
            if (inp.equals("bye")) {
                p = false;
                System.out.println("Bye. Hope to see you again soon!\n");
            } else if (inp.equals("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, lst.get(i));
                }
            } else {
                lst.add(inp);
                System.out.println("added: " + inp);
            }
        }

    }
}
