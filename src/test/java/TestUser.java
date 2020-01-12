import java.util.List;

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

    @Override
    public String toString() {
        return "{" +
                "ID='" + ID + '\'' +
                ", FIRSTNAME='" + FIRSTNAME + '\'' +
                ", LASTNAME='" + LASTNAME + '\'' +
                '}';
    }

    public TestUser(String ID, String FIRSTNAME, String LASTNAME) {
        this.ID = ID;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
    }

}
