package by.allahverdiev.finaltask.service;

public class Validator {

    /**
     * checking invoice number
     * must contain 7 digits
     */
    public boolean checkInvoice(String doc) {
        return doc.matches("\\d{7}");
    }

    public boolean checkPositiveInteger(int count) {
        return count > 0;
    }

    public boolean checkPositiveDouble(double count) {
        return count > 0;
    }
}
