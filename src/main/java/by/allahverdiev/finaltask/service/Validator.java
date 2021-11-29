package by.allahverdiev.finaltask.service;

public class Validator {

    public boolean checkInvoice(String doc) {
        if (doc.matches("\\d{7}")) {
            return true;
        }
        return false;
    }

    public boolean checkPositiveInteger(int count) {
        return count > 0;
    }

    public boolean checkPositiveDouble(double count) {
        return count > 0;
    }
}
