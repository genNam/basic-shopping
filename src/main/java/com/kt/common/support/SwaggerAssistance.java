package com.kt.common.support;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses(value = {
	@ApiResponse(responseCode = "400", description = "검증실패"),
	@ApiResponse(responseCode = "500", description = "서버에러 - 백엔드에 바로 문의 바랍니다.")
})
public abstract class SwaggerAssistance {

	//추상클래스 : 미완성 클래스
	//new를 통해서 인스턴스를 생성할 수 없음
	//오직 상속을 통한 자식 구현 후에 인스턴스 생성 가능
	//보통 상속계층에서 자식멤버의 이름을 통일하는데 사용
}