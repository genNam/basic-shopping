package com.kt.dto.user;

import java.time.LocalDateTime;

import com.kt.domain.user.User;

public class UserResponse {

	public record Detail(

		String id,
		String name,
		String email,
		LocalDateTime createdAt

	){
		public static Detail from(User user){

			return new Detail(
				user.getLoginId(),
				user.getName(),
				user.getEmail(),
				user.getCreatedAt()
			);
		}
	}


}
