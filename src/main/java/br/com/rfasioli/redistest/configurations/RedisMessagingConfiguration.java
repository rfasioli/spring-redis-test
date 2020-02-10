package br.com.rfasioli.redistest.configurations;

import br.com.rfasioli.redistest.messengers.MessagePublisher;
import br.com.rfasioli.redistest.messengers.RedisMessagePublisher;
import br.com.rfasioli.redistest.messengers.RedisMessageSubscriber;
import org.springframework.beans.factory.annotation.Value;
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
    private final String subscriberTopic;
    private final String publisherTopic;

    public RedisMessagingConfiguration(JedisConnectionFactory jedisConnectionFactory,
                                       RedisTemplate<String, Object> redisTemplate,
                                       @Value("${redis.subscriber.topic}") String subscriberTopic,
                                       @Value("${redis.publisher.topic}") String publisherTopic) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.redisTemplate = redisTemplate;
        this.subscriberTopic = subscriberTopic;
        this.publisherTopic = publisherTopic;
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
        container.addMessageListener(messageListener(), subscriberTopic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate, publisherTopic());
    }

    @Bean
    ChannelTopic subscriberTopic() {
        return new ChannelTopic(subscriberTopic);
    }

    @Bean
    ChannelTopic publisherTopic() {
        return new ChannelTopic(publisherTopic);
    }
}
