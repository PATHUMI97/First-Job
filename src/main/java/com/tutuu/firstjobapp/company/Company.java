package com.tutuu.firstjobapp.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutuu.firstjobapp.job.Job;
import com.tutuu.firstjobapp.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

}
