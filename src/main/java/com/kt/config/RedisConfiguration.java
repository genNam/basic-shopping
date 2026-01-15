package com.kt.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.redisson.config.Config;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

	private final RedisProperties redisProperties;

	@Bean
	public RedissonClient redissonClient(){
		var config = new Config();
		var uri = String.format("redis://%s:%s", redisProperties.getHost(),redisProperties.getPort());

		config.useSingleServer().setAddress(uri);

		return Redisson.create(config);
	}
}
