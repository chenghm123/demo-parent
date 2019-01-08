import com.accelerator.demo.standalone.mockito.model.User;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Accelerator on 2016/7/27.
 */
public class MockitoTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test01_mock() {
        String mocked = "mocked Return";
        User user = Mockito.mock(User.class);
        Mockito.when(user.getName()).thenReturn(mocked);
        logger.info("mock user：{}", user.getName());
    }

    @Test
    public void test02_spy() {
        String mocked = "spy Return";
        User user = Mockito.spy(new User());
        Mockito.doReturn(mocked).when(user).getName();
        logger.info("spy user的名称：{}", user.getName());
    }


}
