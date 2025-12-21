package com.kt.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.kt.domain.user.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

//2. 토큰 생성&검증

@Component
@RequiredArgsConstructor
public class JwtService {
	private final JwtProperties jwtProperties;

	//토큰 생성(토큰 구성요소 = 헤더 + 페이로드 +시그니처)
	public String issue(Long id, Role role, Date expiration) {

		return Jwts.builder()
			.subject("basic-shopping")
			.issuer("abc")
			.issuedAt(new Date())
			.id(id.toString())
			.claim("role", role) //권한
			.expiration(expiration)
			.signWith(jwtProperties.getSecret())
			.compact();
	}

	public Date getAccessExpiration() {

		return jwtProperties.getAccessTokenExpiration();
	}

	public Date getRefreshExpiration() {

		return jwtProperties.getRefreshTokenExpiration();
	}

	public boolean validate(String token) {
		return Jwts.parser()
			.verifyWith(jwtProperties.getSecret())
			.build()
			.isSigned(token);
	}

	public Long parseId(String token) {
		var id = Jwts.parser()
			.verifyWith(jwtProperties.getSecret())
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getId();

		return Long.valueOf(id);
	}

	//역할을 token에서 파싱
	public Role parseRole(String token) {
		var role = Jwts.parser()
			.verifyWith(jwtProperties.getSecret())
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.get("role", String.class); //claim에서 "role" 키로 값 가져오기

		return Role.valueOf(role);
	}
}
