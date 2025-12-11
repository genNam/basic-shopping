package com.kt.common.support;


import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;

//조건을 체크하고 예외처리를 일관되게 만드는 클래스

public class Preconditions {

	//static : 객체 생성없이, 전역에서 사용 될수 있다
	public static void validate(boolean expression, ErrorCode errorCode){
		if(!expression){
			throw new CustomException(errorCode);
		}
	}
	//false라면 커스텀 익셉션 객체를 생성하고, 에러코드를 전달

}
