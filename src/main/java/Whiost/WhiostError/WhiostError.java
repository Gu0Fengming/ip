package Whiost.WhiostError;

public class WhiostError {
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