package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class RegisteredUser implements IUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String bio;
    String username;
    String password;
    String profilePicLocation;
    boolean allowAnonymousQuestions;


    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Question> questionsSent;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Question> questionsReceived;




}
