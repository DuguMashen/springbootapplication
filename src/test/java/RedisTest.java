import com.cc.dugumashen.SpringbootApplicationStarter;
import com.cc.dugumashen.service.RedisService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;



import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description：
 * Date: 2019/9/11
 * Author：
 */
@SpringBootTest(classes = SpringbootApplicationStarter.class)
@RunWith(SpringRunner.class)
public class RedisTest {


@Autowired
RedisService redisService;
@Autowired
RedisTemplate redisTemplate;

    @Test
    public void testConnect(){

        String key="key";
        String bord="abc";
        String sss="def";

        redisTemplate.opsForValue().set(key,bord,2,TimeUnit.SECONDS);
        boolean flag=redisTemplate.opsForValue().get(key)==null?true:false;



        System.out.println(flag);
        Object ob=redisTemplate.opsForValue().get(key);

        System.out.println(ob);
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag=redisTemplate.opsForValue().get(key)==null?true:false;
        System.out.println(flag);




    }
}
