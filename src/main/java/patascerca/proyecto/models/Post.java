package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Category category;
    private String description;
    private LocalDate date;
    private String location;
    private Image image;
    @OneToMany(mappedBy = "post")
    private List<Like> likes;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
