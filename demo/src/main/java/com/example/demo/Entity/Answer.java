package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Follow
@Table(name = "answers")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Answer implements IAnswer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String Content;

    private LocalDate publishedDate;

    private String picLocation;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;



}
