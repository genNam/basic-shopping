package com.kt.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.domain.user.User;

public interface UserRepository extends JpaRepository<User,Long> {

	default User findByIdOrThrow(Long id){

		return findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

	}
}
