/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2021-11-03  11:31
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinonc.common.redis.configure.FastJson2JsonRedisSerializer;
import com.sinonc.common.redis.service.RedisService;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisTest {
    public static void main(String[] args) {
        //单机模式
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
        rsc.setPort(6379);
        rsc.setHostName("127.0.0.1");


        RedisTemplate<String, String> template = new RedisTemplate<>();
        //单机模式
        JedisConnectionFactory fac = new JedisConnectionFactory(rsc);
        fac.afterPropertiesSet();

        template.setConnectionFactory(fac);
        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();

        RedisService redisService = new RedisService();
        redisService.redisTemplate = template;
        redisService.lock("test", 1000);
        redisService.del("test");

    }
}
