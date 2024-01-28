package com.novatechzone.spring.longlifeayurveda.domain.comment;

import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
import com.novatechzone.spring.longlifeayurveda.domain.story.Story;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id",insertable = false,updatable = false)
    private Customer customer;

    @Column(name = "attach_path",columnDefinition = "TEXT")
    private String attachPath;

    @Column(name = "story_id")
    private Long storyId;

    @ManyToOne
    @JoinColumn(name = "story_id",referencedColumnName = "id",updatable = false,insertable = false)
    private Story story;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

}
