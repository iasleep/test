import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class TestUserListHttpMessageConverter extends AbstractHttpMessageConverter<TestUserList> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    protected TestUserList readInternal(Class<? extends TestUserList> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(TestUserList testUserList, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
