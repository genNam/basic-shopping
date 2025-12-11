package com.kt.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//공통적으로 사용할 에러코드 모아두는 용도

@Getter
@RequiredArgsConstructor

public enum ErrorCode {

	NOT_FOUND_PRODUCT(HttpStatus.BAD_REQUEST, "상품을 찾을 수 없습니다."),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "필수값 누락입니다.");

	private final HttpStatus status;
	private final String message;

}