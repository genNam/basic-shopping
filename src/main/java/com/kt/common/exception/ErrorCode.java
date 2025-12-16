package com.kt.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//공통적으로 사용할 에러코드 모아두는 용도

@Getter
@RequiredArgsConstructor

public enum ErrorCode {

	//유저
	NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다."),
	FAIL_LOGIN(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),

	//상품
	PRODUCT_TOGGLE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "토글을 사용할 수 없습니다."),
	NOT_FOUND_PRODUCT(HttpStatus.BAD_REQUEST, "상품을 찾을 수 없습니다."),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "필수값 누락입니다.");

	private final HttpStatus status;
	private final String message;

}