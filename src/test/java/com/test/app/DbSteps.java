package com.test.app;

import com.test.app.utils.RandomUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DbSteps {

    String url = "jdbc:postgresql://localhost:5432/testdb";
    String user = "username";
    String password = "password";

    RandomUtils randomUtils = new RandomUtils();
    TestSteps testSteps = new TestSteps();

    private Connection con() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addDbUsers() throws SQLException {
        for (int i = 0; i <= 8; i++) {
            PreparedStatement statement = con()
                    .prepareStatement("INSERT INTO users(ID, FIRSTNAME, LASTNAME) VALUES(NEXTVALUE, ?, ?)");
            statement.setString(1, RandomUtils.genENString());
            statement.setString(2, RandomUtils.genENString());
            statement.executeUpdate();
            statement.close();
        }
    }

    public void deleteAllUsers() throws SQLException {
        PreparedStatement statement = con().prepareStatement("TRUNCATE TABLE users");
        statement.execute();
        statement.close();
    }

    /*
    не совсем уверен в конструкции statement.executeQuery().toString(), но проверить не успеваю
     */
    public List<TestUser> getDBUsers(String id) throws SQLException {
        if(id.isEmpty()) {
            PreparedStatement statement = con().prepareStatement("SELECT * FROM users");
            return testSteps.testUsersList(statement.executeQuery().toString());
        }
        else {
            PreparedStatement statement = con().prepareStatement("SELECT * FROM users WHERE ID=?");
            statement.setString(1, id);
            return testSteps.testUsersList(statement.executeQuery().toString());
        }
    }
}
