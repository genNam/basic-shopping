package com.kt.security;

//3. 인증 필터
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private static final String TOKEN_PREFIX = "Bearer";

	private final JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		//http 요청 헤더 중 "authorization" 부분찾기(이 부분에 '토큰'이 들어있음)
		// Bearer {token}
		var header = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (Strings.isBlank(header)) {
			filterChain.doFilter(request, response);
			return;
		}

		var token = header.substring(TOKEN_PREFIX.length());

		if (!jwtService.validate(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		var id = jwtService.parseId(token);

		//역할
		//TODO : 인가(권한) 로직 추가
		var role = jwtService.parseRole(token);

		var techUpToken = new TechUpAuthenticationToken(
			new DefaultCurrentUser(id, "파싱한아이디"),
			List.of()
		);

		SecurityContextHolder.getContext().setAuthentication(techUpToken);

		filterChain.doFilter(request, response);
	}

}
