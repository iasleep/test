package com.test.app;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TestSteps {

    private static HttpClient client = new DefaultHttpClient();
    private static String URL = "http://localhost:28080/rs/users";

    public List<TestUser> postRequest(String firstName, String lastName) throws IOException {
        HttpPost httpPost = new HttpPost(URL);
        ArrayList params = new ArrayList();
        params.add(new BasicNameValuePair("lastname", lastName));
        params.add(new BasicNameValuePair("firstname", firstName));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = client.execute(httpPost);
        return testUsersList(EntityUtils.toString(response.getEntity()));
    }

    public List<TestUser> getRequest(String id) throws IOException {
        HttpGet httpGet = new HttpGet(URL + "/" + id);
        HttpResponse response = client.execute(httpGet);
        return testUsersList(EntityUtils.toString(response.getEntity()));
    }

    public int deleteRequest(String id) throws IOException {
        HttpDelete httpDelete = new HttpDelete(URL + "/" + id);
        HttpResponse response = client.execute(httpDelete);
        return response.getStatusLine().getStatusCode();
    }

    public List<TestUser> putRequest(String id, String firstName, String lastName) throws IOException {
        HttpPut httpPut = new HttpPut(URL + "/" + id);
        ArrayList params = new ArrayList();
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("lastname", lastName));
        params.add(new BasicNameValuePair("firstname", firstName));
        httpPut.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = client.execute(httpPut);
        return testUsersList(EntityUtils.toString(response.getEntity()));
    }

    public List<TestUser> testUsersList(String httpResponse) {
        List<String> responseList = Arrays.asList(httpResponse.trim().split("},"));
        List<String> parsedResponseList;
        if (responseList.size() > 1) {
            parsedResponseList = responseList.stream().map(s -> {
                if (s.contains("[")) s = s.replace("[", "");
                if (s.contains("]")) s = s.replace("]", "");
                if (!s.contains("}")) s = s + "}";
                return s;
            }).collect(Collectors.toList());
        } else if (responseList.size() == 1) {
            parsedResponseList = responseList.stream().map(s -> {
                s.replace("[", "");
                s.replace("]", "");
                return s;
            }).collect(Collectors.toList());
        } else parsedResponseList = Arrays.asList();

        List<TestUser> testUsers = parsedResponseList.stream().map(s -> {
            TestUser testUser = new TestUser();
            s = s.replace("}", "");
            testUser.setID(s.split("ID=")[1].split(",")[0]);
            testUser.setFIRSTNAME(s.split("FIRSTNAME=")[1].split(",")[0]);
            testUser.setLASTNAME(s.split("LASTNAME=")[1].split(",")[0]);
            return testUser;
        }).collect(Collectors.toList());
        return testUsers;
    }
}
