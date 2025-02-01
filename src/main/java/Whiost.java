import java.util.Scanner;
public class Whiost {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Whiost\nWhat can I do for you?\n";
        System.out.println(greeting);

        boolean p = true;
        Scanner scanner = new Scanner(System.in);
        while (p) {
            String inp = scanner.nextLine();
            if (inp.equals("bye")) {
                p = false;
                System.out.println("Bye. Hope to see you again soon!\n");
            } else {
                System.out.println(inp);
            }
        }

    }
}
