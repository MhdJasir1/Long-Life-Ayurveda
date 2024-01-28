package com.novatechzone.spring.longlifeayurveda.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findByStoryIdAndCustomerId(Long storyId, Long customerId);

}
