package Whiost.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class TaskTest {
    @Test
    public void Task_initializing_success() throws Exception {
        ArrayList<String> test = new ArrayList<String>();
        test.add("[T]");
        test.add("[ ]");
        test.add("test");
        ArrayList<String> test1 = new ArrayList<String>();
        test1.add("test");
        ArrayList<String> test2 = new ArrayList<String>();
        test2.add("[T]");
        assertEquals(test1, new Task(test).lst);
        assertEquals(test2, new Task(test).typeLst);
    }
}