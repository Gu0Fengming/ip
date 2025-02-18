package Whiost.Task;

import java.util.ArrayList;

public class Task {
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