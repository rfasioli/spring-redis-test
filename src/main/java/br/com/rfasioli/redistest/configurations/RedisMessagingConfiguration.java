package br.com.rfasioli.redistest.configurations;

import messagers.MessagePublisher;
import messagers.RedisMessagePublisher;
import messagers.RedisMessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author rfasioli
 *  https://www.baeldung.com/spring-data-redis-pub-sub
 */
@Configuration
@EnableTransactionManagement
public class RedisMessagingConfiguration {

    private final JedisConnectionFactory jedisConnectionFactory;
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisMessagingConfiguration(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, Object> redisTemplate) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.redisTemplate = redisTemplate;
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate, topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("messageQueue");
    }
}
