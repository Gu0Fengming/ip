package Whiost;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import Whiost.Ui.*;
import Whiost.Task.*;
import Whiost.Storage.*;
import Whiost.InputCommand.*;
import Whiost.WhiostError.*;

/**
 * Represents the chatbot itself, initialize and run
 */
public class Whiost {
    public Ui ui;
    public Storage storage;
    public Task task;
    public WhiostError error;

    /**
     * Initialize chatbot Ui, Storage, Task and Errors
     *
     * @param filePath the filepath of a txt file where the tasks store at
     */
    public Whiost(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.task = new Task(this.storage.load());
        this.error = new WhiostError();
    }

    public String greeting(){
        return this.ui.greeting;
    }

    /**
     * Run the chatbot and Looping until exit
     */
    public String run(String inputLine) {
            InputCommand inp = new InputCommand(inputLine);
            int num = -1;
            for (int i = 0; i < inp.response.length; i++) {
                if (inp.response[i] == 1) {
                    num = i;
                    break;
                }
            }
            if (num == 0) { // empty description
                return this.error.showError(1);
            } else if (num == 2) { // list
                if (this.task.lst.size() == 0) {
                    return this.error.showError(3);
                }
                StringBuilder fin = new StringBuilder();
                for (int i = 0; i < this.task.lst.size(); i++) {
                    fin.append((i + 1)).append(".").append(this.task.typeLst.get(i)).append(this.task.markLst.get(i)).append(" ").append(this.task.lst.get(i)).append("\n");
                }
                return fin.toString();
            } else if (num == 3) { // todo
                StringBuilder fin = new StringBuilder();
                fin.append(this.ui.addTask + "\n");
                this.task.typeLst.add("[T]");
                this.task.markLst.add("[ ]");
                this.task.lst.add(inp.name);
                fin.append("  " + "[T]" + "[ ]" + " " + inp.name + "\n");
                fin.append(this.ui.reportTask1 + this.task.lst.size() + this.ui.reportTask2);
                this.storage.save(this.task);
                return fin.toString();
            } else if (num == 4) { // deadline
                StringBuilder fin = new StringBuilder();
                fin.append(this.ui.addTask + "\n");
                this.task.typeLst.add("[D]");
                this.task.markLst.add("[ ]");
                int pos = inp.target;
                if (inp.timeChecker == 1) {
                    this.task.lst.add(inp.name + "(by " + this.ui.monthTrans[Integer.parseInt(inp.month) - 1] + " " + inp.date + ", " + inp.year + ")");
                    fin.append("  " + "[D]" + "[ ]" + " " + inp.name + "(by " + this.ui.monthTrans[Integer.parseInt(inp.month) - 1] + " " + inp.date + ", " + inp.year + ")" + "\n");
                } else {
                    this.task.lst.add(inputLine.substring(9, pos) + "(" + inputLine.substring(pos + 1) + ")");
                    fin.append("  " + "[D]" + "[ ]" + " " + inp.name + "(" + inp.time + ")" + "\n");
                }
                fin.append(this.ui.reportTask1 + this.task.lst.size() + this.ui.reportTask2);
                this.storage.save(this.task);
                return fin.toString();
            } else if (num == 5) { // event
                StringBuilder fin = new StringBuilder();
                fin.append(this.ui.addTask + "\n");
                this.task.typeLst.add("[E]");
                this.task.markLst.add("[ ]");
                int pos1 = inp.target1;
                int pos2 = inp.target2;
                this.task.lst.add(inp.name + "(from:" + inp.startTime + " to:" + inp.endTime + ")");
                fin.append("  " + "[E]" + "[ ]" + inp.name + "(from:" + inp.startTime + " to:" + inp.endTime + ")" + "\n");
                fin.append(this.ui.reportTask1 + this.task.lst.size() + this.ui.reportTask2);
                this.storage.save(this.task);
                return fin.toString();
            } else if (num == 6) { // mark
                int pos = inp.target;
                if (pos >= this.task.lst.size()) {
                    return this.error.showError(2);
                }
                StringBuilder fin = new StringBuilder();
                fin.append(this.ui.marked + "\n");
                this.task.markLst.set(pos, "[X]");
                fin.append("  " + this.task.typeLst.get(pos) + this.task.markLst.get(pos) + " " + this.task.lst.get(pos));
                this.storage.save(this.task);
                return fin.toString();
            } else if (num == 7) { // unmark
                int pos = inp.target;
                if (pos >= this.task.lst.size()) {
                    return this.error.showError(2);
                }
                StringBuilder fin = new StringBuilder();
                fin.append(this.ui.unmarked + "\n");
                this.task.markLst.set(pos, "[ ]");
                fin.append("  " + this.task.typeLst.get(pos) + this.task.markLst.get(pos) + " " + this.task.lst.get(pos));
                this.storage.save(this.task);
                return fin.toString();
            } else if (num == 8) { // delete
                int pos = inp.target;
                if (pos >= this.task.lst.size()) {
                    return this.error.showError(2);
                }
                StringBuilder fin = new StringBuilder();
                fin.append(this.ui.deleted + "\n");
                fin.append("  " + this.task.typeLst.get(pos) + this.task.markLst.get(pos) + this.task.lst.get(pos) + "\n");
                this.task.lst.remove(pos);
                this.task.markLst.remove(pos);
                this.task.typeLst.remove(pos);
                fin.append(this.ui.reportTask1 + this.task.lst.size() + this.ui.reportTask2);
                this.storage.save(this.task);
                return fin.toString();
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
                    return this.error.showError(4);
                } else {
                    StringBuilder fin = new StringBuilder();
                    fin.append(this.ui.finded + "\n");
                    for (int i = 0; i < finded.size(); i++) {
                        fin.append((i + 1) + "." + this.task.typeLst.get(finded.get(i)) + this.task.markLst.get(finded.get(i)) + " " + this.task.lst.get(finded.get(i)) + "\n");
                    }
                    return fin.toString();
                }

            } else {
                return this.error.showError(0);
            }
    }

    public static void main(String[] args) {
        System.out.println("programme running");
    }
}