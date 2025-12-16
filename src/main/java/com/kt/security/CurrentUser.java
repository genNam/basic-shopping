package com.kt.security;

//컨트롤러에서 사용할 인터페이스
//보안 구현 세부 내용은 숨기고 지금 로그인한 user의 id만 가져옴

public interface CurrentUser {
	Long getId();

	String getLoginId();
}
