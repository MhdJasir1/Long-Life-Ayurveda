package com.novatechzone.spring.longlifeayurveda.domain.story;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import com.novatechzone.spring.longlifeayurveda.domain.rating.RatingRequestDTO;
import com.novatechzone.spring.longlifeayurveda.domain.rating.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
public class StoryResource {
    private final StoryService storyService;

    @PostMapping("/add_story/{app_id}")
    public ResponseEntity<?> addStory(@PathVariable("app_id") String appId, @RequestParam("description") String description, @RequestBody MultipartFile file) throws IOException{
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return storyService.addStory(description,file);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid app id");
        }
    }


    @GetMapping("/all_stories/{app_id}")
    public ResponseEntity<?> getStories(@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return storyService.getStories();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }
}
