package com.kt.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

//1.JWT 설정

@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt") //yml 설정을 담을 어노테이션으로, jwt 부분만 읽음
public class JwtProperties {

	private final String secret;
	private Long accessTokenExpiration; //유효기간
	private Long refreshTokenExpiration; //유효기간

	public Date getAccessTokenExpiration(){

		return new Date(new Date().getTime() + accessTokenExpiration);
	}
	public Date getRefreshTokenExpiration(){

		return new Date(new Date().getTime() + refreshTokenExpiration);
	}

	public SecretKey getSecret(){

		return Keys.hmacShaKeyFor(secret.getBytes());
	}

}
