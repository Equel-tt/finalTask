package by.allahverdiev.finaltask.service;

public class Validator {
    public boolean checkInvoice(String doc) {
        if (doc.matches("\\d{7}")) {
            return true;
        }
        return false;
    }
}
