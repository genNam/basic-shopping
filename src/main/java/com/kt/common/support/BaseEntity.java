package com.kt.common.support;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass //매핑하지 않겠다
public class BaseEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 자동으로 생성해주는 어노테이션
	//Identity : 기본키 생성을 데이터베이스에게 위임
	private Long id;

}