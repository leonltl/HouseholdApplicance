

import com.leonard.applicance.ApplicanceServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicanceServiceApplication.class)
@WebAppConfiguration
public class ApplicanceApplicationTests {

    @Test
    public void contextLoads() {
    }

}
