package be.pxl.ja.streamingservice.model;

public class CreditCardNumber {
    private static final int LENGTH = 16;
    private CreditCardType type;
    private String number;
    private String cvc;

    public CreditCardNumber(CreditCardType type, String number, String cvc) {
        this.type = type;
        this.number = number;
        this.cvc = cvc;
    }
}
