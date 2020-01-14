package com.test.app;

public class TestUser {

    private String ID;
    private String FIRSTNAME;
    private String LASTNAME;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public TestUser() {}

    @Override
    public String toString() {
        return "{" +
                "ID='" + ID + '\'' +
                ", FIRSTNAME='" + FIRSTNAME + '\'' +
                ", LASTNAME='" + LASTNAME + '\'' +
                '}';
    }
}
