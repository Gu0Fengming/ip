package Whiost.Storage;

import java.io.*;
import java.util.ArrayList;
import Whiost.Task.*;

/**
 * Represent the function of storage including saving and loading
 */
public class Storage {
    public String filePath;

    /**
     * Set the file path to where the main function indicated
     *
     * @param filePath the filepath of a txt file where the tasks store at
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the tasks in txt file into initLst
     *
     * @return the tasks in txt file without splitting
     */
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

    /**
     * Save the current tasks into txt file
     *
     * @param task current tasks
     */
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