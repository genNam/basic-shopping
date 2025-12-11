package com.kt.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

//api 응답을 일관된 형태로 통일하기 위해

@Getter
@AllArgsConstructor
public class ApiResult<T> {

	private String name;
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	public static ApiResult<Void> ok() {

		return ApiResult.of("ok", "성공", null);
	}

	public static <T> ApiResult<T> ok(T data) {
		return ApiResult.of("ok", "성공", data);

	}
	//<T>는 제네릭 메서드라는걸 명시해주기 위함이고, 클래스 <T>와는 별개의 값
	//<T>는 반환타입, 메서드 파라미터로 메서드에서 사용됨

	private static <T> ApiResult of(String code, String message, T data) {
		return new ApiResult<>(code, message, data);
	}
}
