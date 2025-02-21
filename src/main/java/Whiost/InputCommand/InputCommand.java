package Whiost.InputCommand;

/**
 * Check and handle user input
 */
public class InputCommand {
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

    /**
     * Check the user inputs and set response command to main class
     *
     * @param userInput user input
     */
    public InputCommand(String userInput) {
        this.response = new int[11];
        for (int i = 0; i < 11; i++) {
            this.response[i] = 0;
        }
        if (userInput.length() >= 5 && userInput.substring(0, 5).equals("todo ")) {             //3
            String check = "";
            for (int i = 0; i < userInput.length() - 5; i++) {
                check += " ";
            }
            if (userInput.equals("todo " + check)) {
                this.response[0] = 1;
            } else {
                this.name = userInput.substring(5);
                this.response[3] = 1;
            }
        } else if (userInput.equals("list")) {                //2
            this.response[2] = 1;
        } else if (userInput.length() >= 9 && userInput.substring(0, 9).equals("deadline ")) {       //4
            String check = "";
            for (int i = 0; i < userInput.length() - 9; i++) {
                check += " ";
            }
            if (userInput.equals("deadline " + check)) {
                this.response[0] = 1;
            } else {
                this.target = userInput.indexOf('/');
                String sub = userInput.substring(this.target + 1);
                if (sub.length() > 10 && sub.charAt(7) == '-' && sub.charAt(10) == '-') {
                    this.timeChecker = 1;
                    this.month = sub.substring(8, 10);
                    this.date = sub.substring(11, 13);
                    this.year = sub.substring(3, 7);
                } else {
                    this.time = userInput.substring(this.target + 1);
                    this.timeChecker = 0;
                }
                this.name = userInput.substring(9, this.target);
                this.response[4] = 1;
            }
        } else if (userInput.length() >= 6 && userInput.substring(0, 6).equals("event ")) {          //5
            String check = "";
            for (int i = 0; i < userInput.length() - 6; i++) {
                check += " ";
            }
            if (userInput.equals("event " + check)) {
                this.response[0] = 1;
            } else {
                this.target1 = userInput.indexOf('/');
                this.target2 = this.target1 + 1 + userInput.substring(this.target1 + 1).indexOf('/');
                this.name = userInput.substring(6, this.target1);
                this.startTime = userInput.substring(this.target1 + 5, this.target2);
                this.endTime = userInput.substring(this.target2 + 4);
                this.response[5] = 1;
            }
        } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("mark ")) {           //6
            String check = "";
            for (int i = 0; i < userInput.length() - 5; i++) {
                check += " ";
            }
            if (userInput.equals("mark " + check)) {
                this.response[0] = 1;
            } else {
                this.target = Integer.parseInt(String.valueOf(userInput.charAt(5))) - 1;
                this.response[6] = 1;
            }
        } else if (userInput.length() >= 7 && userInput.substring(0, 7).equals("unmark ")) {         //7
            String check = "";
            for (int i = 0; i < userInput.length() - 7; i++) {
                check += " ";
            }
            if (userInput.equals("unmark " + check)) {
                this.response[0] = 1;
            } else {
                this.target = Integer.parseInt(String.valueOf(userInput.charAt(7))) - 1;
                this.response[7] = 1;
            }
        } else if (userInput.length() >= 7 && userInput.substring(0, 7).equals("delete ")) {         //8
            String check = "";
            for (int i = 0; i < userInput.length() - 7; i++) {
                check += " ";
            }
            if (userInput.equals("delete " + check)) {
                this.response[0] = 1;
            } else {
                this.target = Integer.parseInt(String.valueOf(userInput.charAt(7))) - 1;
                this.response[8] = 1;
            }
        } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("find ")){           //9
            String check = "";
            for (int i = 0; i < userInput.length() - 5; i++) {
                check += " ";
            }
            if (userInput.equals("find " + check)) {
                this.response[0] = 1;
            } else {
                this.name = userInput.substring(5);
                this.response[9] = 1;
            }
        } else {                              //10
            this.response[10] = 1;
        }
    }
}