package com.kt.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable // 새로운 값 타입을 직접 정의해서 사용할때 사용
@NoArgsConstructor
@AllArgsConstructor
public class Receiver {

	@Column(name = "receiver_name")
	private String receiverName;

	@Column(name = "receiver_address")
	private String receiverAddress;

	@Column(name = "receiver_mobile")
	private String mobile;

}
