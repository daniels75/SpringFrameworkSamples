package org.daniels.interview;

public class Participant {

    String name;
    int code;

    public Participant(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public int getCampaignCode() {
        return code;
    }


    public CustomerProfile getCustomerProfile() {
        return new CustomerProfile(name + code);
    }

    public String toString() {
        return name;
    }
}
