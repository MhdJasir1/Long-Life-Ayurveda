package com.novatechzone.spring.longlifeayurveda.domain.comment;

import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerRepository;
import com.novatechzone.spring.longlifeayurveda.domain.rating.Rating;
import com.novatechzone.spring.longlifeayurveda.domain.rating.RatingRepository;
import com.novatechzone.spring.longlifeayurveda.domain.story.StoryRepository;
import com.novatechzone.spring.longlifeayurveda.dto.RequestMetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CustomerRepository customerRepository;
    private final StoryRepository storyRepository;
    private final RequestMetaDTO requestMetaDTO;
    private final CommentRepository commentRepository;


    public ResponseEntity<?> addComment(Long storyId, CommentRequestDTO commentRequestDTO) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }else if (String.valueOf(storyId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }else{

            if (commentRepository.findByStoryIdAndCustomerId(storyId,requestMetaDTO.getCustomerId()) != null){
                return ResponseEntity.status(HttpStatus.OK).body("Already commented");
            }else{

                if (storyRepository.findById(storyId).isEmpty()){
                    return ResponseEntity.status(HttpStatus.OK).body("Story not found");
                } else {

                    Comment comment = new Comment();
                    comment.setCustomerId(requestMetaDTO.getCustomerId());
                    comment.setStoryId(Long.valueOf(storyId.toString()));
                    comment.setMessage(commentRequestDTO.getMessage());
                    comment.setCreatedAt(String.valueOf(LocalDateTime.now()));
                    commentRepository.save(comment);

                    return ResponseEntity.status(HttpStatus.OK).body("Commented successfully");
                }
            }
        }
    }

    public ResponseEntity<?> getComments() {
        List<Comment> allComments = commentRepository.findAll();
        ArrayList<Comment> commentArrayList = new ArrayList<>();
        allComments.forEach(product -> {
            commentArrayList.add(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(commentArrayList);
    }
}
