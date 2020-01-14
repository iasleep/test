package com.test.app;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.test.app.utils.RandomUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UsersTests extends Assert {

    private static RandomUtils random = new RandomUtils();
    private static TestSteps testSteps = new TestSteps();
    private static DbSteps dbSteps = new DbSteps();

    @BeforeClass
    public static void setup() throws SQLException {
        dbSteps.addDbUsers();
    }

    @Test
    public void addUserTest() throws IOException, SQLException {
        String lastName = RandomUtils.genENString();
        String firstName = RandomUtils.genENString();
        List<TestUser> apiUsers = testSteps.postRequest(firstName, lastName);
        assertEquals("Received 1 user", 1, apiUsers.size());
        TestUser addedUser = apiUsers.get(0);
        assertTrue("Response has firstname", addedUser.getFIRSTNAME().contains(firstName));
        assertTrue("Response has lastname", addedUser.getLASTNAME().contains(lastName));
        assertFalse("Response has ID", addedUser.getID().isEmpty());

        String id = addedUser.getID();
        List<TestUser> dbUsers = dbSteps.getDBUsers(id);
        assertEquals("Db has user with id=" + id,
                1, dbUsers.size());
        assertSame("DB and API users are the same", dbUsers.get(0), addedUser);
    }

    @Test
    public void getAllUsersTest() throws IOException, SQLException {
        List<TestUser> apiUsers = testSteps.getRequest("");
        List<TestUser> dbUsers = dbSteps.getDBUsers("");
        assertEquals("Users number from DB and API are the same",
                apiUsers.size(), dbUsers.size());
        assertTrue("API and DB users are the same",
                apiUsers.containsAll(dbUsers));
    }

    @Test
    public void getSingleUser() throws IOException, SQLException {
        List<TestUser> apiUsers = testSteps.getRequest("");
        String id = apiUsers.get(RandomUtils.genInt(0, apiUsers.size() - 1)).getID();

        List<TestUser> singleApiUser = testSteps.getRequest(id);
        assertEquals("Received 1 user",
                1, singleApiUser.size());

        List<TestUser> singleDbUser = dbSteps.getDBUsers(id);
        assertSame("API and DB users are the same",
                singleApiUser, singleDbUser);
    }

    @Test
    public void editUser() throws IOException, SQLException {
        List<TestUser> apiUsers = testSteps.getRequest("");
        String id = apiUsers.get(RandomUtils.genInt(0, apiUsers.size() - 1)).getID();

        String newFirstName = RandomUtils.genENString();
        String newLastName = RandomUtils.genENString();
        List<TestUser> editedApiUser = testSteps.putRequest(id, newFirstName, newLastName);
        assertEquals("Received 1 user",
                1, editedApiUser.size());

        assertSame("First name changed to " + newFirstName,
                editedApiUser.get(0).getFIRSTNAME(), newFirstName);
        assertSame("Last name changed to " + newLastName,
                editedApiUser.get(0).getLASTNAME(), newLastName);
        TestUser dbUser = dbSteps.getDBUsers(id).get(0);

        assertSame("Api and DB users are the same",
                dbUser, editedApiUser.get(0));
    }

    @Test
    public void deleteUser() throws IOException, SQLException {
        List<TestUser> apiUsers = testSteps.getRequest("");
        String id = apiUsers.get(RandomUtils.genInt(0, apiUsers.size() - 1)).getID();

        int statusCode = testSteps.deleteRequest(id);
        assertEquals("Delete user status code is 200", 200, statusCode);

        List<TestUser> dbUsers = dbSteps.getDBUsers(id);
        assertTrue("User has been deleted from DB", dbUsers.isEmpty());
    }

    @AfterClass
    private static void cleanDB() throws SQLException {
        dbSteps.deleteAllUsers();
    }
}