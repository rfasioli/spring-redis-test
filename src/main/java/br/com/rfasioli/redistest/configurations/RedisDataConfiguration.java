package br.com.rfasioli.redistest.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author rfasioli
 *  https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#reference
 */
@Configuration
@EnableTransactionManagement
public class RedisDataConfiguration {
    private final String hostName;
    private final Integer port;

    public RedisDataConfiguration(
            @Value("${redis.hostName}") String hostName,
            @Value("${redis.port}") Integer port
    ) {
        this.hostName = hostName;
        this.port = port;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(hostName, port);
        return new JedisConnectionFactory(config);
    }

    /**
     * aconselhável a instalação em cluster e utilização do sentinal para alta disponibilidade
     */
//    @Bean
//    public RedisConnectionFactory jedisConnectionFactory() {
//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
//                .master("mymaster")
//                .sentinel("127.0.0.1", 26379)
//                .sentinel("127.0.0.1", 26380);
//        return new JedisConnectionFactory(sentinelConfig);
//    }

    /**
     * Configuração pode ficar no spring
     * spring.redis.sentinel.master: name of the master node.
     * spring.redis.sentinel.nodes: Comma delimited list of host:port pairs.
     * spring.redis.sentinel.password: The password to apply when authenticating with Redis Sentinel
     */

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setEnableTransactionSupport(true);
        return template;
    }

    /**
     * Para tratar transação do data source
     */
//    @Bean
//    public PlatformTransactionManager transactionManager() throws SQLException {
//        return new DataSourceTransactionManager(dataSource());
//    }
}
