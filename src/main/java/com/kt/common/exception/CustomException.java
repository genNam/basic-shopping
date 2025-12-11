package com.kt.common.exception;

import lombok.Getter;

//에러를 만드는 객체

@Getter
public class CustomException extends RuntimeException {

	//파라미터로 받은 에러코드를 필드로 가지고,

	private final ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) //그 에러코드를 기반으로 생성자 만듦
	{
		super(errorCode.getMessage()); //에러코드 클래스에서 해당하는 메시지를 가져와서 슈퍼(부모 클래스)에게 전달
		//즉 런타임익셉션 클래스에게 전달해서 런타임에러를 발생시키겠다
		this.errorCode = errorCode;
	}
}