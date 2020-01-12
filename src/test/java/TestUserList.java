import java.util.ArrayList;
import java.util.List;

public class TestUserList {

    private List<TestUser> testUserList;

    public TestUserList() {
        testUserList = new ArrayList<>();
    }

    public List<TestUser> getTestUserList() {
        return testUserList;
    }

    public void setTestUserList(List<TestUser> testUserList) {
        this.testUserList = testUserList;
    }
}
