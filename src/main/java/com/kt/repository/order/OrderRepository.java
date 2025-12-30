package com.kt.repository.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

	Optional<Order> findByIdAndUserId(Long userId, Long orderId);
}
