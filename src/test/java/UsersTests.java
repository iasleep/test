import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.RandomUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UsersTests extends Assert {

    private static RandomUtils random = new RandomUtils();
    private static TestSteps testSteps = new TestSteps();

    @BeforeClass
    public static void setup() {
        IntStream.rangeClosed(1, 8).forEach(i -> {
            try {
                testSteps.postRequest(random.genENString(), random.genENString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void addUser() throws IOException {

        String lastName = random.genENString();
        String firstName = random.genENString();
        String[] response = testSteps.postRequest(firstName, lastName);
        assertTrue("response has firstname", response[1].contains(firstName));
        assertTrue("response has lastname", response[2].contains(lastName));
        assertTrue("response has ID", !response[0].split("=")[1].isEmpty());
                /*
        ещё тут нужна проверка в БД созданной учетной записи
         */
    }

    @Test
    public void getUsers() throws IOException {
        TestUser[] response = testSteps.getRequest("");
        System.out.println(response.toString());
    }
}