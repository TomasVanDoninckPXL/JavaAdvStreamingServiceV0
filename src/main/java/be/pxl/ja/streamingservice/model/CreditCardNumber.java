package be.pxl.ja.streamingservice.model;

public class CreditCardNumber {
    private static final int LENGTH = 16;
    private CreditCardType type;
    private String number;
    private String cvc;

    public CreditCardNumber(String number, String cvc) {
        number = removeBlanks(number);
        if (!isNumeric(number) || number.length() != LENGTH){
            throw new IllegalArgumentException("Must have " + LENGTH + " digits.");
        }
        this.number = number;
        type = getCreditCardType(number);
        if (type == null){
            throw new IllegalArgumentException("This is not a valid credit card.");
        }
        this.cvc = removeBlanks(cvc);
    }

    private CreditCardType getCreditCardType(String number) {
        if (number.charAt(0) == '5'){
            return CreditCardType.MASTERCARD;
        }
        else if (number.charAt(0) == '4'){
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
