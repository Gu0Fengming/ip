package Whiost;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import Whiost.Ui.*;
import Whiost.Task.*;
import Whiost.Storage.*;
import Whiost.InputCommand.*;
import Whiost.WhiostError.*;


public class Whiost {
    public Ui ui;
    public Storage storage;
    public Task task;
    public WhiostError error;

    public Whiost(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.task = new Task(this.storage.load());
        this.error = new WhiostError();
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
            } else if (num == 9) { //find
                ArrayList<Integer> finded = new ArrayList<>();
                for (int i = 0; i < this.task.lst.size(); i++) {
                    String cur = this.task.lst.get(i);
                    for (int j = 0; j < cur.length() - inp.name.length() + 1; j++){
                        if (Objects.equals(inp.name, cur.substring(j, j + inp.name.length()))){
                            finded.add(i);
                            break;
                        }
                    }
                }
                if (finded.size() == 0){
                    this.error.showError(4);
                    continue;
                } else {
                    System.out.println(this.ui.finded);
                    for (int i = 0; i < finded.size(); i++) {
                        System.out.println((i + 1) + "." + this.task.typeLst.get(finded.get(i)) + this.task.markLst.get(finded.get(i)) + " " + this.task.lst.get(finded.get(i)));
                    }
                }

            } else {
                this.error.showError(0);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Whiost("./src/data/whiost.txt").run();
    }
}