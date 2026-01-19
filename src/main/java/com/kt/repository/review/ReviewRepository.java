package com.kt.repository.review;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByProductId(Long productId);

	Optional<Review> findByIdAndUserId(Long id,Long userId);

	List<Review> findByUserId(Long userId);


}
