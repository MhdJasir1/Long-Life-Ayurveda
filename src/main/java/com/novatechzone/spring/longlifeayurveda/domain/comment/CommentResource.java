package com.novatechzone.spring.longlifeayurveda.domain.comment;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import com.novatechzone.spring.longlifeayurveda.domain.rating.RatingRequestDTO;
import com.novatechzone.spring.longlifeayurveda.domain.rating.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentResource {
    private final CommentService commentService;

    @PostMapping("/add_comment/{story_id}/{app_id}")
    public ResponseEntity<?> addComment(@PathVariable("app_id") String appId, @PathVariable("story_id") Long storyId, @Valid @RequestBody CommentRequestDTO commentRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return commentService.addComment(storyId,commentRequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid app id");
        }
    }


    @GetMapping("/all_comments/{app_id}")
    public ResponseEntity<?> getComments(@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return commentService.getComments();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }
}
