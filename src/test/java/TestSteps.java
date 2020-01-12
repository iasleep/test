import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import utils.HeaderRequestInterceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.converter.StringHttpMessageConverter.DEFAULT_CHARSET;


public class TestSteps {

    private static HttpClient client = new DefaultHttpClient();
    private static String URL = "http://localhost:28080/rs/users";
    private Object List;

//    private static HashMap<String, String> apply(String s1) {
//        final HashMap<String, String> stringStringHashMap = new HashMap<String, String>(s1.split("=")[0], s1.split("=")[1]);
//        return stringStringHashMap;
//    }

    public String[] postRequest(String firstName, String lastName) throws IOException {

        HttpPost httpPost = new HttpPost(URL);

        ArrayList params = new ArrayList();
        params.add(new BasicNameValuePair("lastname", lastName));
        params.add(new BasicNameValuePair("firstname", firstName));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = client.execute(httpPost);
        return EntityUtils.toString(response.getEntity())
                .replace("[{", "")
                .replace("}]", "")
                .split(",");
    }

//    public List<List<BasicNameValuePair>> getRequest(String id) throws IOException {
//
//        HttpGet httpGet = new HttpGet(URL + "/" + id);
//        HttpResponse response = client.execute(httpGet);
//        String result = EntityUtils.toString(response.getEntity());
//        String[] strings = EntityUtils.toString(response.getEntity())
//                .replace("[{", "")
//                .replace("}]", "")
//                .split("}, \\{");
//        return Arrays.stream(strings).map(s -> {
//            return Arrays.stream(s.trim().split(",")).map(TestSteps::apply).collect(Collectors.toList());
//        }).collect(Collectors.toList());
//    }
//
    public TestUser[] getRequest(String id)  {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TestUser[]> response =
                restTemplate.getForEntity(
                        URL,
                        TestUser[].class);
        return response.getBody();
    }



}
