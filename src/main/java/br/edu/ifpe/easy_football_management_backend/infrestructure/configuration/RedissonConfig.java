package br.edu.ifpe.easy_football_management_backend.infrestructure.configuration;

import jakarta.validation.Valid;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${ADDRESS_REDIS}")
    private String address;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config
            .useSingleServer()
            .setAddress(address);

        return Redisson.create(config);
    }
}
