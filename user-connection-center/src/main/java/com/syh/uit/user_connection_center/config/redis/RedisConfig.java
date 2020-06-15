package com.syh.uit.user_connection_center.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@EnableCaching
@Configuration()
public class RedisConfig {

    public RedisConnectionFactory connectionFactory(MultiSourceRedisProperties.SingleRedisProperties properties ) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(properties.getJedis().getPool().getMaxActive());
        poolConfig.setMaxIdle(properties.getJedis().getPool().getMaxIdle());
        poolConfig.setMaxWaitMillis(properties.getJedis().getPool().getMaxWait().toMillis());
        poolConfig.setMinIdle(properties.getJedis().getPool().getMinIdle());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(true);

        JedisClientConfiguration.JedisClientConfigurationBuilder clientConfigBuilder = JedisClientConfiguration.builder();
        clientConfigBuilder.usePooling().poolConfig(poolConfig);
        //.and().readTimeout().build();
        if (properties.getTimeout()!=null){
            clientConfigBuilder.readTimeout(properties.getTimeout());
        }
        JedisClientConfiguration clientConfig = clientConfigBuilder.build();
        // 单点redis
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        // 哨兵redis
        // RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
        // 集群redis
        // RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
        redisConfig.setHostName(properties.getHost());
        redisConfig.setPassword(RedisPassword.of(properties.getPassword()));
        redisConfig.setPort(properties.getPort());
        redisConfig.setDatabase(properties.getDatabase());

        return new JedisConnectionFactory(redisConfig,clientConfig);
    }

    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> jacksonSerial = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerial.setObjectMapper(om);

        // 值采用json序列化
        template.setValueSerializer(jacksonSerial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSerial);
        template.afterPropertiesSet();

        return template;
    }

    @Bean("online_stateRedis")
    RedisTemplate<String,Object> online_stateRedis(MultiSourceRedisProperties redisProperties){
        return redisTemplate(connectionFactory(redisProperties.getOnlineState()));
    }

    @Bean("messageTSRedis")
    RedisTemplate<String,Object> messageTSRedis(MultiSourceRedisProperties redisProperties){
        return redisTemplate(connectionFactory(redisProperties.getMessageTemporaryStorage()));
    }
}
