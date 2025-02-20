package Whiost.WhiostError;

/**
 * Represent possible errors triggered by users and how does the chatbot would say when it triggered
 */
public class WhiostError {
    /**
     * Return different sentences when user triggered different errors
     *
     * @param num error number
     */
    public String showError(int num) {
        if (num == 0) {
            return ("OOPS!!! I'm sorry, but I don't know what that means.");
        } else if (num == 1) {
            return ("OOPS!!! The description cannot be empty.");
        } else if (num == 2) {
            return("OOPS!!! The task you select doesn't exist.");
        } else if (num == 3) {
            return("OOPS!!! There's no task.");
        } else if (num == 4) {
            return("OOPS!!! There's no task matching.");
        } else {
            return("No Such Error");
        }
    }
}