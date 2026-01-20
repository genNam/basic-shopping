package com.kt.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.common.exception.CustomException;
import com.kt.common.exception.ErrorCode;
import com.kt.domain.user.Role;
import com.kt.domain.user.User;

public interface UserRepository extends JpaRepository<User,Long> {

	//조회 결과가 없을때 repository가 처리, 반드시 있어야 함
	default User findByIdOrThrow(Long id){

		return findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

	}

	//조회 결과가 없을때 호출한 쪽이 처리, 있을수도 없을수도 있음
	Optional<User> findByLoginId(String loginId);

	//관리자 권한을 가진 user만 조회
	List<User> findByRole(Role role);
}
