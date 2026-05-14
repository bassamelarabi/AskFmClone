package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question implements IQuestion{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String content;
    private LocalDate publishedDate;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private RegisteredUser sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private RegisteredUser receiver;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;


    @OneToMany(mappedBy = "question")
    private List<Reaction> reactions;



}
