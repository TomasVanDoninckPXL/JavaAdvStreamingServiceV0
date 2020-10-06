package be.pxl.ja.streamingservice.model;

import org.jetbrains.annotations.NotNull;

public class CreditCardNumber {
    private static final int LENGTH = 16;
    private static final int CVC_LENGTH = 3;

    private CreditCardType type;
    private String number;
    private String cvc;

    public CreditCardNumber(String number, String cvc) {
        this.number = validateNumberAndRemoveBlanks(number);
        this.type = validateCardType();
        this.cvc = validateCvcAndRemoveBlanks(cvc);
    }

    private CreditCardType validateCardType() {
        CreditCardType type = getCreditCardType(this.number);
        if (type == null){
            throw new IllegalArgumentException("This is not a valid credit card.");
        }
        return type;
    }

    @NotNull
    private String validateCvcAndRemoveBlanks(String cvc) {
        cvc = removeBlanks(cvc);
        if (!isNumeric(cvc) || cvc.length() != CVC_LENGTH){
            throw new IllegalArgumentException("CVC should be 3 digits");
        }
        return cvc;
    }

    @NotNull
    private String validateNumberAndRemoveBlanks(String number) {
        number = removeBlanks(number);
        if (!isNumeric(number) || number.length() != LENGTH){
            throw new IllegalArgumentException("Must have " + LENGTH + " digits.");
        }
        return number;
    }

    private CreditCardType getCreditCardType(String number) {
        if (Integer.parseInt(number.substring(0,1)) == CreditCardType.MASTERCARD.getFirstNumber()){
            return CreditCardType.MASTERCARD;
        }
        else if (Integer.parseInt(number.substring(0,1)) == CreditCardType.VISA.getFirstNumber()){
            return CreditCardType.VISA;
        }
        else {
            return null;
        }
    }

    private boolean isNumeric(String number) {
        boolean onlyDigits = true;
        char[] numbers = number.toCharArray();
        for (char a:numbers) {
            if (onlyDigits && !Character.isDigit(a)){
                onlyDigits = false;
            }
        }
        return onlyDigits;
    }

    private String removeBlanks(String number){
        char[] numbers = number.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char a:numbers){
            if (a != ' '){
                result.append(a);
            }
        }
        return result.toString();
    }

    public CreditCardType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getCvc() {
        return cvc;
    }
}
