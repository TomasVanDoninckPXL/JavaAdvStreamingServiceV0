package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.security.NoSuchAlgorithmException;

public class Account {
    private String email;
    private String password;
    private StreamingPlan streamingPlan;
    private Profile[] profiles;
    private PaymentInfo paymentInfo;

    public Account(String email, String password, StreamingPlan streamingPlan, Profile[] profiles, PaymentInfo paymentInfo) {
        this.email = email;
        this.password = password;
        this.streamingPlan = streamingPlan;
        this.profiles = profiles;
        this.paymentInfo = paymentInfo;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    Account(StreamingPlan streamingPlan){
        this.streamingPlan = streamingPlan;
        profiles = new Profile[streamingPlan.getNumberOfProfiles()];
        profiles[0] = new Profile("profile 1");
    }

    public Profile getFirstProfile(){
        return profiles[0];
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StreamingPlan getStreamingPlan() {
        return streamingPlan;
    }

    public void setStreamingPlan(StreamingPlan streamingPlan) {
        this.streamingPlan = streamingPlan;
    }

    public Profile[] getProfiles() {
        return profiles;
    }

    public void setProfiles(Profile[] profiles) {
        this.profiles = profiles;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public boolean verifyPassword(String password) throws NoSuchAlgorithmException {
        return PasswordUtil.isValid(password, this.password);
    }
}
