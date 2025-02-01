import java.util.Scanner;
public class Whiost {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Whiost\nWhat can I do for you?\n";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        if (x.equals("exit")) {
            System.out.println("Bye. Hope to see you again soon!\n");
        }
    }
}
