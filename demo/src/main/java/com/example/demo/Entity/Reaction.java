package com.example.demo.Entity;

import jakarta.persistence.*;


@Follow
@Table(name = "reactions")
public class Reaction implements IReaction{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long reactionId;

    @ManyToOne
    @JoinColumn(name ="question_id")
    Question question;
    @Override
    public String getReactionType() {
        return "Like";
    }
}
