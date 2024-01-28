package com.novatechzone.spring.longlifeayurveda.domain.story;

import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerRepository;
import com.novatechzone.spring.longlifeayurveda.dto.RequestMetaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final CustomerRepository customerRepository;
    private final StoryRepository storyRepository;
    private final RequestMetaDTO requestMetaDTO;

    public static final String UPLOAD_DIR = "story_upload";

    public ResponseEntity<?> addStory(String description, MultipartFile file) throws IOException {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else {

            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)){
                Files.createDirectory(path);
            }
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = System.currentTimeMillis() +"."+extension;

            Path filePath = path.resolve(fileName);
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

            String url = UPLOAD_DIR +"/"+fileName;

            Story story = new Story();
            story.setCustomerId(requestMetaDTO.getCustomerId());
            story.setDescription(description);
            story.setCreatedAt(String.valueOf(LocalDateTime.now()));
            story.setAttachPath(url);
            storyRepository.save(story);

            return ResponseEntity.status(HttpStatus.OK).body("Story added successfully");
        }
    }

    public ResponseEntity<?> getStories() {
        List<Story> allStories = storyRepository.findAll();
        ArrayList<Story> storyArrayList = new ArrayList<>();
        allStories.forEach(product -> {
            storyArrayList.add(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(storyArrayList);
    }
}
