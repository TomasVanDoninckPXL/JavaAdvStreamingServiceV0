package be.pxl.ja.streamingservice.model;

public enum StreamingPlan {
    BASIC(1, 7.99),
    STANDAARD(2, 11.99),
    PREIMIUM(4, 15.99);

    private int numberOfProfiles;
    private double price;

    StreamingPlan(int numberOfProfiles, double price){
        this.numberOfProfiles = numberOfProfiles;
        this.price = price;
    }

    public int getNumberOfProfiles(){
        return numberOfProfiles;
    }
}
