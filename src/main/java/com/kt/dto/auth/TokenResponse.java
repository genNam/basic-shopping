package com.kt.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record TokenResponse(

	@NotBlank
	String accessToken,
	@NotBlank
	String refreshToken
) {
	public static TokenResponse of(String accessToken, String refreshToken)
	{
		return new TokenResponse(

			accessToken,
			refreshToken

		);
	}
}
