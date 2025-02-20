package Whiost.Storage;

import java.io.*;
import java.util.ArrayList;
import Whiost.Task.*;

public class StorageTest {
    public String filePath;

    public StorageTest(String filePath) {
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