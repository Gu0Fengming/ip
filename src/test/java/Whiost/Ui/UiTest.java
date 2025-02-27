package Whiost.Ui;

public class UiTest {
    public String greeting;
    public String addTask;
    public String marked;
    public String unmarked;
    public String reportTask1;
    public String reportTask2;
    public String deleted;
    public String[] monthTrans;

    public UiTest() {
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