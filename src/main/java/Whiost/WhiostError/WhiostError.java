package Whiost.WhiostError;

/**
 * Represent possible errors triggered by users and how does the chatbot would say when it triggered
 */
public class WhiostError {
    /**
     * Say different sentences when user triggered different errors
     *
     * @param num error number
     */
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