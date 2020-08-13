package com.feeder.flashsale;

import com.feeder.flashsale.entity.Goods;

import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestRedis {


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test() {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("k1","v1");

        Assert.assertEquals("v1", ops.get("k1"));

        ops.set("goods", new Goods(1l).setName("shouji"));
        Assert.assertEquals(new Goods(1l).setName("shouji"), ops.get("goods"));

    }

    @After
    public void teardown() {
        redisTemplate.delete(Lists.newArrayList("k1", "goods"));
    }
}
