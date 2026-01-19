package com.kt.common.support.redis;

import java.time.Duration;

import org.redisson.client.RedisClient;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.redisson.api.RBucket;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

	//상수
	private final static String REFRESH_TOKEN_PREFIX = "refresh-token";

	//레디스를 객체처럼 다룰 수 있게 해주는 라이브러리
	private final RedissonClient redissonClient;

	public void saveRefreshToken(String token, Long userId, Long expiration) {

		String key = REFRESH_TOKEN_PREFIX + token; //redis key 생성
		//RBucket : redisson에서 key-value 객체를 담을 수 있는 가방
		RBucket<Long> bucket = redissonClient.getBucket(
			key); //이 key에 해당하는 버킷을 다룰 수 있는 참조를 가져옴 //버킷이 실제로 redis에 존재하지 않아도 에러나지 않음
		bucket.set(userId, Duration.ofSeconds(expiration)); //실제로 값을 저장(value로 userId 저장
		//key는 token, value는 userId
		//expiration 시간 동안 유효함
	}

	public Long findUserIdByRefreshToken(String token){

		String key = REFRESH_TOKEN_PREFIX + token;
		RBucket<Long> bucket = redissonClient.getBucket(key);
		return bucket.get();
	}

	public void deleteRefreshToken(String token){

		String key = REFRESH_TOKEN_PREFIX + token;
		RBucket<Long> bucket = redissonClient.getBucket(key);
		bucket.delete();
	}

}
