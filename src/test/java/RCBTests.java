import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;


@Log4j2
public class RCBTests {
    @Test
    public void test1(){
        System.out.println("Test Started");
        log.info("Hiiiiiiiiiiiiiiiiiiii");
        System.out.println("Test Started");
    }

}
