package com.kt.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record TokenReissueRequest(

	@NotBlank
	String refreshToken
){

}
