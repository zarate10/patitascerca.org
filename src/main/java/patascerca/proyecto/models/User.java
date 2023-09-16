package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String password;
    private String email;
    private String phone;
    private String location;
    private Image image;
    private String description;
    private LocalDate registerDate;
    private Integer range;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> commentList;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> postList;
}
