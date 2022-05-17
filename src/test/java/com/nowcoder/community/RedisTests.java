package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.concurrent.TimeUnit;

/**
 * @author xindong
 * @create 2022-05-17 20:18
 */

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStrings() {
        String redisKey = "test";
        redisTemplate.opsForValue().set(redisKey, 1);//这种还有一些类似操作都是数据库操作
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));
    }

    @Test
    public void testHashes() {
        String redisKey = "test:user";
        redisTemplate.opsForHash().put(redisKey, "id", 1);
        redisTemplate.opsForHash().put(redisKey, "username", "xindong");

        System.out.println(redisTemplate.opsForHash().get(redisKey, "id"));
        System.out.println(redisTemplate.opsForHash().get(redisKey, "username"));

    }

    @Test
    public void testLists() {
        String redisKey = "test:ids";
        redisTemplate.opsForList().leftPush(redisKey, 101);
        redisTemplate.opsForList().leftPush(redisKey, 102);
        redisTemplate.opsForList().leftPush(redisKey, 103);

        System.out.println(redisTemplate.opsForList().size(redisKey));
        System.out.println(redisTemplate.opsForList().index(redisKey, 1));
        System.out.println(redisTemplate.opsForList().range(redisKey, 1, 2));

        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));

    }

    //无序集合
    @Test
    public void testSets() {
        String redisKey = "test:teachers";

        redisTemplate.opsForSet().add(redisKey, "刘备", "张飞", "诸葛亮", "关羽", "赵云");

        System.out.println(redisTemplate.opsForSet().size(redisKey));
        //随机弹出一个
        System.out.println(redisTemplate.opsForSet().pop(redisKey));
        System.out.println(redisTemplate.opsForSet().members(redisKey));
    }

    //有序集合，按照权值排序
    @Test
    public void testZSets() {
        String redisKey = "test:students";

        redisTemplate.opsForZSet().add(redisKey, "唐僧", 1);
        redisTemplate.opsForZSet().add(redisKey, "悟空", 2);
        redisTemplate.opsForZSet().add(redisKey, "八戒", 3);
        redisTemplate.opsForZSet().add(redisKey, "沙僧", 4);
        redisTemplate.opsForZSet().add(redisKey, "白龙马", 5);

        System.out.println(redisTemplate.opsForZSet().zCard(redisKey));
        System.out.println(redisTemplate.opsForZSet().score(redisKey, "唐僧"));
        System.out.println(redisTemplate.opsForZSet().rank(redisKey, "沙僧"));
        System.out.println(redisTemplate.opsForZSet().reverseRank(redisKey, "沙僧"));
        System.out.println(redisTemplate.opsForZSet().range(redisKey, 1, 3));
        System.out.println(redisTemplate.opsForZSet().reverseRange(redisKey, 1, 3));
    }

    @Test
    public void testOthers() {
        //设置数据的过期时间，要设置数据的单位，时间到了就不能查到数据
        redisTemplate.expire("test", 100, TimeUnit.SECONDS);
    }

    //多次访问同一个键时的绑定，就是原先的方法调用太冗长了，使用这个用啥方法直接调就行
    @Test
    public void testBound(){
        String redisKey = "test:teachers";
        BoundSetOperations operations = redisTemplate.boundSetOps(redisKey);
        System.out.println(operations.members());
    }

    //redis中使用编程式事务来精确定位要执行事务的代码，声明式事务范围较大，最小也是一个方法
    // 编程式事务
    @Test
    public void testTransactional() {
        Object obj = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey = "test:tx";

                operations.multi();//启用事务

                //以下数据库操作会被先加入队列然后统一执行，因此在事务中是查不到数据的，最后返回受影响的行数和查询结果
                //第一次执行111，之后000，因为set不重复存数据

                operations.opsForSet().add(redisKey, "zhangsan");
                operations.opsForSet().add(redisKey, "lisi");
                operations.opsForSet().add(redisKey, "wangwu");

                System.out.println(operations.opsForSet().members(redisKey));

                return operations.exec();//执行事务
            }
        });
        System.out.println(obj);
    }
}
