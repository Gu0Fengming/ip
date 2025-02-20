package Whiost.InputCommand;

public class InputCommandTest {
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

    public InputCommandTest(String inp) {
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