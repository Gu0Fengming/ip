import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Whiost {
    public static void save(List<String> lst, List<String> typeLst, List<String> markLst) {
        try {
            PrintWriter data = new PrintWriter(new FileWriter("./data/whiost.txt"));
            for (int i = 0; i < lst.size(); i++) {
                data.write(typeLst.get(i));
                data.write("\n");
                data.write(markLst.get(i));
                data.write("\n");
                data.write(lst.get(i));
                data.write("\n");
            }
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Whiost\nWhat can I do for you?\n";
        String addTask = "Got it. I've added this task:";
        String marked = "Nice! I've marked this task as done:";
        String unmarked = "OK, I've marked this task as not done yet:";
        String reportTask1 = "Now you have ";
        String reportTask2 = " tasks in the list.";
        String deleted = "Noted. I've removed this task:";

        String error0 = "OOPS!!! I'm sorry, but I don't know what that means.";
        String errorEmpty = "OOPS!!! The description cannot be empty.";
        String errorNotFound = "OOPS!!! The task you select doesn't exist.";
        String errorEmptyList = "OOPS!!! There's no task.";

        // Print greeting
        System.out.print(greeting);

        boolean p = true;
        List<String> lst = new ArrayList<String>();
        List<String> typeLst = new ArrayList<String>();
        List<String> markLst = new ArrayList<String>();

        try {
            BufferedReader data = new BufferedReader(new FileReader("./data/whiost.txt"));
            List<String> temp_lst = new ArrayList<String>();
            String line;
            while ((line = data.readLine()) != null) {
                temp_lst.add(line);
            }
            System.out.println(temp_lst);
            int state = 0;
            for (int i = 0; i < temp_lst.size(); i++) {
                if (state == 0) {
                    state = 1;
                    typeLst.add(temp_lst.get(i).length() > 0 ? temp_lst.get(i).substring(0, temp_lst.get(i).length()-0) : "");
                } else if (state == 1) {
                    state = 2;
                    markLst.add(temp_lst.get(i).length() > 0 ? temp_lst.get(i).substring(0, temp_lst.get(i).length()-0) : "");
                } else {
                    state = 0;
                    lst.add(temp_lst.get(i).length() > 0 ? temp_lst.get(i).substring(0, temp_lst.get(i).length()-0) : "");
                }
            }
            System.out.println(typeLst);
            System.out.println(markLst);
            System.out.println(lst);
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Scanner scanner = new Scanner(System.in);
        while (p) {
            String inp = scanner.nextLine();
            if (inp.equals("todo") || inp.equals("todo ") ||
                    inp.equals("deadline") || inp.equals("deadline ") ||
                    inp.equals("event") || inp.equals("event ") ||
                    inp.equals("mark") || inp.equals("mark ") ||
                    inp.equals("unmark") || inp.equals("unmark ") ||
                    inp.equals("delete") || inp.equals("delete ")) {
                System.out.println(errorEmpty);
                continue;
            } else if (inp.equals("bye")) {
                p = false;
                System.out.println("Bye. Hope to see you again soon!\n");
                save(lst, typeLst, markLst);
            } else if (inp.equals("list")) {
                if (lst.size() == 0) {
                    System.out.println(errorEmptyList);
                }
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println((i + 1) + "." + typeLst.get(i) + markLst.get(i) + " " + lst.get(i));
                }
            } else if (inp.length() >= 5 && inp.substring(0, 5).equals("todo ")) {
                System.out.println(addTask);
                typeLst.add("[T]");
                markLst.add("[ ]");
                lst.add(inp.substring(5));
                System.out.println("  " + "[T]" + "[ ]" + " " + inp.substring(5));
                System.out.println(reportTask1 + lst.size() + reportTask2);
                save(lst, typeLst, markLst);
            } else if (inp.length() >= 9 && inp.substring(0, 9).equals("deadline ")) {
                System.out.println(addTask);
                typeLst.add("[D]");
                markLst.add("[ ]");
                int pos = inp.indexOf('/');
                lst.add(inp.substring(9, pos) + "(" + inp.substring(pos + 1) + ")");
                System.out.println("  " + "[D]" + "[ ]" + " " + inp.substring(9, pos) + "(" + inp.substring(pos + 1) + ")");
                System.out.println(reportTask1 + lst.size() + reportTask2);
                save(lst, typeLst, markLst);
            } else if (inp.length() >= 6 && inp.substring(0, 6).equals("event ")) {
                System.out.println(addTask);
                typeLst.add("[E]");
                markLst.add("[ ]");
                int pos1 = inp.indexOf('/');
                int pos2 = pos1 + inp.substring(pos1 + 1).indexOf('/');
                lst.add(inp.substring(6, pos1) + "(from:" + inp.substring(pos1 + 5, pos2) + " to:" + inp.substring(pos2 + 4) + ")");
                System.out.println("  " + "[E]" + "[ ]" + inp.substring(6, pos1) + "(from:" + inp.substring(pos1 + 5, pos2) + " to:" + inp.substring(pos2 + 4) + ")");
                System.out.println(reportTask1 + lst.size() + reportTask2);
                save(lst, typeLst, markLst);
            } else if (inp.length() >= 5 && inp.substring(0, 5).equals("mark ")) {
                int pos = Integer.parseInt(inp.substring(5, 6)) - 1;
                if (pos >= lst.size()) {
                    System.out.println(errorNotFound);
                    continue;
                }
                System.out.println(marked);
                markLst.set(pos, "[X]");
                System.out.println("  " + typeLst.get(pos) + markLst.get(pos) + " " + lst.get(pos));
                save(lst, typeLst, markLst);
            } else if (inp.length() >= 7 && inp.substring(0, 7).equals("unmark ")) {
                int pos = Integer.parseInt(inp.substring(7, 8)) - 1;
                if (pos >= lst.size()) {
                    System.out.println(errorNotFound);
                    continue;
                }
                System.out.println(unmarked);
                markLst.set(pos, "[ ]");
                System.out.println("  " + typeLst.get(pos) + markLst.get(pos) + " " + lst.get(pos));
                save(lst, typeLst, markLst);
            } else if (inp.length() >= 7 && inp.substring(0, 7).equals("delete ")) {
                int pos = Integer.parseInt(inp.substring(7, 8)) - 1;
                if (pos >= lst.size()) {
                    System.out.println(errorNotFound);
                    continue;
                }
                System.out.println(deleted);
                System.out.println("  " + typeLst.get(pos) + markLst.get(pos) + lst.get(pos));
                lst.remove(pos);
                markLst.remove(pos);
                typeLst.remove(pos);
                System.out.println(reportTask1 + lst.size() + reportTask2);
                save(lst, typeLst, markLst);
            } else {
                System.out.println(error0);
            }
        }
        scanner.close();
    }
}
