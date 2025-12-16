package com.kt.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//구현체
//인증이 끝난 후의 사용자

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultCurrentUser implements UserDetails, CurrentUser {

	private Long id; //현재 id
	private String loginId; //파싱한 아이디

	@Override
	//서비스, 컨트롤러 등에서 사용자를 식별하기 위해 구현
	public Long getId() {
		return id;
	}

	@Override
	public String getLoginId() {
		return loginId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//사용자 권한 목록을 반환
		return List.of();
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	//스프링 시큐리티 내부에서 사용자를 식별하기 위해 구현
	public String getUsername() {
		return id.toString();
	}
}
