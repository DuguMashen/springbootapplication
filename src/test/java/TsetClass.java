import org.junit.Test;

import java.security.SecureRandom;

/**
 * Description：
 * Date: 2019/9/7
 * Author：
 */
public class TsetClass {
    @Test
    public void tses(){
        SecureRandom secureRandom=new SecureRandom();
        secureRandom.setSeed("aaaa".getBytes());
        Long lg=secureRandom.nextLong();
        System.out.println(lg);
    }
}
