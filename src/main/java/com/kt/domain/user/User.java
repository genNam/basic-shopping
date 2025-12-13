package com.kt.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kt.domain.order.Order;
import com.kt.common.support.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseEntity {

	private String loginId;
	private String password;
	private String name;
	private String email;
	private String mobile;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private LocalDate birthday;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<>();

	public User(String loginId, String password, String name, String email,
		String mobile, Gender gender, LocalDate birthday,LocalDateTime createdAt,
		LocalDateTime updatedAt, Role role){

		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.birthday = birthday;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
	}

	public static User create(String loginId, String password,
		String name, String email, String mobile, Gender gender,
		LocalDate birthday,LocalDateTime createdAt, LocalDateTime updatedAt){

		return new User(
			loginId,
			password,
			name,
			email,
			mobile,
			gender,
			birthday,
			createdAt,
			updatedAt,
			Role.USER
		);
	}

	public void update(String name, String email, String mobile){
		//영속성 컨텍스트에서 관리하는 엔티티를 수정
		//JPA는 현재 엔티티랑 처음 저장된 값(스냅샷)을 비교해 변경된걸 감지하고 UPDATE 쿼리를 생성

		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}







}
