import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Ui {
    public String greeting;
    public String addTask;
    public String marked;
    public String unmarked;
    public String reportTask1;
    public String reportTask2;
    public String deleted;
    public String[] monthTrans;

    public Ui() {
        this.greeting = "Hello! I'm Whiost\nWhat can I do for you?\n";
        this.addTask = "Got it. I've added this task:";
        this.marked = "Nice! I've marked this task as done:";
        this.unmarked = "OK, I've marked this task as not done yet:";
        this.reportTask1 = "Now you have ";
        this.reportTask2 = " tasks in the list.";
        this.deleted = "Noted. I've removed this task:";

        this.monthTrans = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    }
}

class Error {
    public void showError(int num) {
        if (num == 0) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
        } else if (num == 1) {
            System.out.println("OOPS!!! The description cannot be empty.");
        } else if (num == 2) {
            System.out.println("OOPS!!! The task you select doesn't exist.");
        } else if (num == 3) {
            System.out.println("OOPS!!! There's no task.");
        } else {
            System.out.println("No Such Error");
        }
    }
}

class Task {
    public ArrayList<String> lst = new ArrayList<>();
    public ArrayList<String> typeLst = new ArrayList<>();
    public ArrayList<String> markLst = new ArrayList<>();
    public ArrayList<String> initLst;

    public Task(ArrayList<String> initLst) {
        this.initLst = initLst;
        int state = 0;
        for (int i = 0; i < this.initLst.size(); i++) {
            String line = this.initLst.get(i);
            String trimmed = (line.length() > 0 ? line.substring(0, line.length() - 1) : line);
            if (state == 0) {
                state = 1;
                this.typeLst.add(trimmed);
            } else if (state == 1) {
                state = 2;
                this.markLst.add(trimmed);
            } else {
                state = 0;
                this.lst.add(trimmed);
            }
        }
    }
}

class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load() {
        ArrayList<String> initLst = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            BufferedReader data = new BufferedReader(new FileReader(file));
            String line;
            while ((line = data.readLine()) != null) {
                initLst.add(line);
            }
            System.out.println(initLst);
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return initLst;
    }

    public void save(Task task) {
        try {
            BufferedWriter data = new BufferedWriter(new FileWriter(this.filePath));
            for (int i = 0; i < task.lst.size(); i++) {
                data.write(task.typeLst.get(i));
                data.newLine();
                data.write(task.markLst.get(i));
                data.newLine();
                data.write(task.lst.get(i));
                data.newLine();
            }
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class InputCommand {
    public int[] response;
    public String name;
    public int target;
    public int target1;
    public int target2;
    public int timeChecker;
    public String month;
    public String date;
    public String year;
    public String time;
    public String startTime;
    public String endTime;

    public InputCommand(String inp) {
        this.response = new int[10];
        for (int i = 0; i < 10; i++) {
            this.response[i] = 0;
        }
        if (inp.length() >= 5 && inp.substring(0, 5).equals("todo ")) {             //3
            String check = "";
            for (int i = 0; i < inp.length() - 5; i++) {
                check += " ";
            }
            if (inp.equals("todo " + check)) {
                this.response[0] = 1;
            } else {
                this.name = inp.substring(5);
                this.response[3] = 1;
            }
        } else if (inp.equals("bye")) {                 //1
            this.response[1] = 1;
        } else if (inp.equals("list")) {                //2
            this.response[2] = 1;
        } else if (inp.length() >= 9 && inp.substring(0, 9).equals("deadline ")) {       //4
            String check = "";
            for (int i = 0; i < inp.length() - 9; i++) {
                check += " ";
            }
            if (inp.equals("deadline " + check)) {
                this.response[0] = 1;
            } else {
                this.target = inp.indexOf('/');
                String sub = inp.substring(this.target + 1);
                if (sub.length() > 10 && sub.charAt(7) == '-' && sub.charAt(10) == '-') {
                    this.timeChecker = 1;
                    this.month = sub.substring(8, 10);
                    this.date = sub.substring(11, 13);
                    this.year = sub.substring(3, 7);
                } else {
                    this.time = inp.substring(this.target + 1);
                    this.timeChecker = 0;
                }
                this.name = inp.substring(9, this.target);
                this.response[4] = 1;
            }
        } else if (inp.length() >= 6 && inp.substring(0, 6).equals("event ")) {          //5
            String check = "";
            for (int i = 0; i < inp.length() - 6; i++) {
                check += " ";
            }
            if (inp.equals("event " + check)) {
                this.response[0] = 1;
            } else {
                this.target1 = inp.indexOf('/');
                this.target2 = this.target1 + 1 + inp.substring(this.target1 + 1).indexOf('/');
                this.name = inp.substring(6, this.target1);
                this.startTime = inp.substring(this.target1 + 5, this.target2);
                this.endTime = inp.substring(this.target2 + 4);
                this.response[5] = 1;
            }
        } else if (inp.length() >= 5 && inp.substring(0, 5).equals("mark ")) {           //6
            String check = "";
            for (int i = 0; i < inp.length() - 5; i++) {
                check += " ";
            }
            if (inp.equals("mark " + check)) {
                this.response[0] = 1;
            } else {
                this.target = Integer.parseInt(String.valueOf(inp.charAt(5))) - 1;
                this.response[6] = 1;
            }
        } else if (inp.length() >= 7 && inp.substring(0, 7).equals("unmark ")) {         //7
            String check = "";
            for (int i = 0; i < inp.length() - 7; i++) {
                check += " ";
            }
            if (inp.equals("unmark " + check)) {
                this.response[0] = 1;
            } else {
                this.target = Integer.parseInt(String.valueOf(inp.charAt(7))) - 1;
                this.response[7] = 1;
            }
        } else if (inp.length() >= 7 && inp.substring(0, 7).equals("delete ")) {         //8
            String check = "";
            for (int i = 0; i < inp.length() - 7; i++) {
                check += " ";
            }
            if (inp.equals("delete " + check)) {
                this.response[0] = 1;
            } else {
                this.target = Integer.parseInt(String.valueOf(inp.charAt(7))) - 1;
                this.response[8] = 1;
            }
        } else {                              //9
            this.response[9] = 1;
        }
    }
}

public class Whiost {
    public Ui ui;
    public Storage storage;
    public Task task;
    public Error error;

    public Whiost(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.task = new Task(this.storage.load());
        this.error = new Error();
    }

    public void run() {
        System.out.println(this.ui.greeting);
        boolean operate = true;
        Scanner scanner = new Scanner(System.in);
        while (operate) {
            String inputLine = scanner.nextLine();
            InputCommand inp = new InputCommand(inputLine);
            int num = -1;
            for (int i = 0; i < inp.response.length; i++) {
                if (inp.response[i] == 1) {
                    num = i;
                    break;
                }
            }
            if (num == 0) { // empty description
                this.error.showError(1);
                continue;
            } else if (num == 1) { // exit
                operate = false;
                System.out.println("Bye. Hope to see you again soon!\n");
                this.storage.save(this.task);
            } else if (num == 2) { // list
                if (this.task.lst.size() == 0) {
                    this.error.showError(3);
                }
                for (int i = 0; i < this.task.lst.size(); i++) {
                    System.out.println((i + 1) + "." + this.task.typeLst.get(i) + this.task.markLst.get(i) + " " + this.task.lst.get(i));
                }
            } else if (num == 3) { // todo
                System.out.println(this.ui.addTask);
                this.task.typeLst.add("[T]");
                this.task.markLst.add("[ ]");
                this.task.lst.add(inp.name);
                System.out.println("  " + "[T]" + "[ ]" + " " + inp.name);
                System.out.println(this.ui.reportTask1 + this.task.lst.size() + this.ui.reportTask2);
                this.storage.save(this.task);
            } else if (num == 4) { // deadline
                System.out.println(this.ui.addTask);
                this.task.typeLst.add("[D]");
                this.task.markLst.add("[ ]");
                int pos = inp.target;
                if (inp.timeChecker == 1) {
                    this.task.lst.add(inp.name + "(by " + this.ui.monthTrans[Integer.parseInt(inp.month) - 1] + " " + inp.date + ", " + inp.year + ")");
                    System.out.println("  " + "[D]" + "[ ]" + " " + inp.name + "(by " + this.ui.monthTrans[Integer.parseInt(inp.month) - 1] + " " + inp.date + ", " + inp.year + ")");
                } else {
                    this.task.lst.add(inputLine.substring(9, pos) + "(" + inputLine.substring(pos + 1) + ")");
                    System.out.println("  " + "[D]" + "[ ]" + " " + inp.name + "(" + inp.time + ")");
                }
                System.out.println(this.ui.reportTask1 + this.task.lst.size() + this.ui.reportTask2);
                this.storage.save(this.task);
            } else if (num == 5) { // event
                System.out.println(this.ui.addTask);
                this.task.typeLst.add("[E]");
                this.task.markLst.add("[ ]");
                int pos1 = inp.target1;
                int pos2 = inp.target2;
                this.task.lst.add(inp.name + "(from:" + inp.startTime + " to:" + inp.endTime + ")");
                System.out.println("  " + "[E]" + "[ ]" + inp.name + "(from:" + inp.startTime + " to:" + inp.endTime + ")");
                System.out.println(this.ui.reportTask1 + this.task.lst.size() + this.ui.reportTask2);
                this.storage.save(this.task);
            } else if (num == 6) { // mark
                int pos = inp.target;
                if (pos >= this.task.lst.size()) {
                    this.error.showError(2);
                    continue;
                }
                System.out.println(this.ui.marked);
                this.task.markLst.set(pos, "[X]");
                System.out.println("  " + this.task.typeLst.get(pos) + this.task.markLst.get(pos) + " " + this.task.lst.get(pos));
                this.storage.save(this.task);
            } else if (num == 7) { // unmark
                int pos = inp.target;
                if (pos >= this.task.lst.size()) {
                    this.error.showError(2);
                    continue;
                }
                System.out.println(this.ui.unmarked);
                this.task.markLst.set(pos, "[ ]");
                System.out.println("  " + this.task.typeLst.get(pos) + this.task.markLst.get(pos) + " " + this.task.lst.get(pos));
                this.storage.save(this.task);
            } else if (num == 8) { // delete
                int pos = inp.target;
                if (pos >= this.task.lst.size()) {
                    this.error.showError(2);
                    continue;
                }
                System.out.println(this.ui.deleted);
                System.out.println("  " + this.task.typeLst.get(pos) + this.task.markLst.get(pos) + this.task.lst.get(pos));
                this.task.lst.remove(pos);
                this.task.markLst.remove(pos);
                this.task.typeLst.remove(pos);
                System.out.println(this.ui.reportTask1 + this.task.lst.size() + this.ui.reportTask2);
                this.storage.save(this.task);
            } else {
                this.error.showError(0);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Whiost("./data/whiost.txt").run();
    }
}
