package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity(name = "users")
@Table(name = "users")
public class RegisteredUser implements IUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String bio;
    @NotNull
    String username;
    String password;
    String profilePicLocation;
    boolean allowAnonymousQuestions = true;


    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Question> questionsSent;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Question> questionsReceived;






}
